package com.ayd2.imporcomgua.services.payment;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.payment.NewPaymentRequestDTO;
import com.ayd2.imporcomgua.dto.payment.PaymentResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.exceptions.PaymentExceedsSaleBalanceException;
import com.ayd2.imporcomgua.mappers.payment.PaymentMapper;
import com.ayd2.imporcomgua.models.payment.Bank;
import com.ayd2.imporcomgua.models.payment.Payment;
import com.ayd2.imporcomgua.models.sale.PaymentStatus;
import com.ayd2.imporcomgua.models.sale.Sale;
import com.ayd2.imporcomgua.repositories.payment.BankRepository;
import com.ayd2.imporcomgua.repositories.payment.PaymentRepository;
import com.ayd2.imporcomgua.repositories.sale.SaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;
    private final SaleRepository saleRepository;
    private final BankRepository bankRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public PaymentResponseDTO createPayment(NewPaymentRequestDTO paymentRequestDTO)
            throws NotFoundException, PaymentExceedsSaleBalanceException, DuplicatedEntityException {
        Sale sale = saleRepository.findById(paymentRequestDTO.saleId())
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        // 2. Validar que exista el banco
        Bank bank = bankRepository.findById(paymentRequestDTO.bankId())
                .orElseThrow(() -> new NotFoundException("Banco no encontrado"));

        if (sale.getPaymentStatus() == PaymentStatus.PAGADO) {
            throw new DuplicatedEntityException("La venta ya está pagada");
        }

        if (paymentRequestDTO.amount() > sale.getRemainingBalance()) {
            throw new PaymentExceedsSaleBalanceException(paymentRequestDTO.amount(), sale.getRemainingBalance());
        }

        // 4. Crear el pago usando mapper
        Payment payment = paymentMapper.toPayment(paymentRequestDTO);
        payment.setBank(bank);
        payment.setSale(sale);

        // 5. Actualizar saldo y estado de la venta
        updateSalePaymentStatus(sale, paymentRequestDTO.amount());

        // 6. Guardar cambios
        Payment savedPayment = paymentRepository.save(payment);
        saleRepository.save(sale);

        // 7. Retornar respuesta usando mapper
        return paymentMapper.toPaymentResponseDTO(savedPayment);
    }

    private void updateSalePaymentStatus(Sale sale, Double paymentAmount) {
        double newBalance = sale.getRemainingBalance() - paymentAmount;
        sale.setRemainingBalance(newBalance);
        
        if (newBalance <= 0) {
            sale.setPaymentStatus(PaymentStatus.PAGADO);
            sale.setPaymentDate(LocalDate.now());
        } else {
            sale.setPaymentStatus(PaymentStatus.PARCIAL);
        }
    }
    
}
