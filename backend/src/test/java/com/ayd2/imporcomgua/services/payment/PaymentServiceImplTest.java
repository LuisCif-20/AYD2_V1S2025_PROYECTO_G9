package com.ayd2.imporcomgua.services.payment;

import com.ayd2.imporcomgua.dto.payment.NewPaymentRequestDTO;
import com.ayd2.imporcomgua.dto.payment.PaymentResponseDTO;
import com.ayd2.imporcomgua.exceptions.*;
import com.ayd2.imporcomgua.mappers.payment.PaymentMapper;
import com.ayd2.imporcomgua.models.payment.Bank;
import com.ayd2.imporcomgua.models.payment.Payment;
import com.ayd2.imporcomgua.models.sale.PaymentStatus;
import com.ayd2.imporcomgua.models.sale.Sale;
import com.ayd2.imporcomgua.repositories.payment.BankRepository;
import com.ayd2.imporcomgua.repositories.payment.PaymentRepository;
import com.ayd2.imporcomgua.repositories.sale.SaleRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    private static final UUID SALE_ID = UUID.randomUUID();
    private static final UUID BANK_ID = UUID.randomUUID();
    private static final UUID PAYMENT_ID = UUID.randomUUID();
    private static final LocalDate PAYMENT_DATE = LocalDate.now();
    private static final String ACCOUNT_NUMBER = "123456789";
    private static final String TRANSACTION_NUMBER = "TX123456";
    private static final Double PAYMENT_AMOUNT = 1000.0;
    private static final Double SALE_BALANCE = 1500.0;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private BankRepository bankRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @InjectMocks
    private PaymentServiceImpl serviceToTest;

    @Test
    void testCreatePaymentSuccessfully() throws Exception {
        // Arrange
        NewPaymentRequestDTO requestDTO = new NewPaymentRequestDTO(
                SALE_ID, BANK_ID, PAYMENT_DATE, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, PAYMENT_AMOUNT);
        
        Sale sale = new Sale();
        sale.setId(SALE_ID);
        sale.setRemainingBalance(SALE_BALANCE);
        sale.setPaymentStatus(PaymentStatus.PENDIENTE);
        
        Bank bank = new Bank();
        bank.setId(BANK_ID);
        
        Payment payment = new Payment();
        payment.setReceiptNumber(PAYMENT_ID);
        payment.setAmount(PAYMENT_AMOUNT);
        payment.setPaymentDate(PAYMENT_DATE);
        payment.setAccountNumber(ACCOUNT_NUMBER);
        payment.setTransactionNumber(TRANSACTION_NUMBER);
        payment.setSale(sale);
        payment.setBank(bank);
        
        PaymentResponseDTO responseDTO = new PaymentResponseDTO(
                PAYMENT_ID, null, null, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, 
                PAYMENT_AMOUNT, PAYMENT_DATE);

        when(saleRepository.findById(SALE_ID)).thenReturn(Optional.of(sale));
        when(bankRepository.findById(BANK_ID)).thenReturn(Optional.of(bank));
        when(paymentMapper.toPayment(requestDTO)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(payment);
        when(paymentMapper.toPaymentResponseDTO(payment)).thenReturn(responseDTO);

        // Act
        PaymentResponseDTO result = serviceToTest.createPayment(requestDTO);

        // Assert
        assertAll(
                () -> assertEquals(PAYMENT_ID, result.receiptNumber()),
                () -> assertEquals(ACCOUNT_NUMBER, result.accountNumber()),
                () -> assertEquals(TRANSACTION_NUMBER, result.transactionNumber()),
                () -> assertEquals(PAYMENT_AMOUNT, result.amount()),
                () -> assertEquals(PAYMENT_DATE, result.paymentDate())
        );

        verify(saleRepository, times(1)).findById(SALE_ID);
        verify(bankRepository, times(1)).findById(BANK_ID);
        verify(paymentMapper, times(1)).toPayment(requestDTO);
        verify(paymentRepository, times(1)).save(payment);
        verify(saleRepository, times(1)).save(sale);
        
        // Verify sale was updated correctly
        ArgumentCaptor<Sale> saleCaptor = ArgumentCaptor.forClass(Sale.class);
        verify(saleRepository).save(saleCaptor.capture());
        assertEquals(SALE_BALANCE - PAYMENT_AMOUNT, saleCaptor.getValue().getRemainingBalance());
        assertEquals(PaymentStatus.PARCIAL, saleCaptor.getValue().getPaymentStatus());
    }

    @Test
    void testCreatePaymentWhenSaleNotFound() {
        // Arrange
        NewPaymentRequestDTO requestDTO = new NewPaymentRequestDTO(
                SALE_ID, BANK_ID, PAYMENT_DATE, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, PAYMENT_AMOUNT);

        when(saleRepository.findById(SALE_ID)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.createPayment(requestDTO));

        assertEquals("Venta no encontrada", exception.getMessage());

        verify(saleRepository, times(1)).findById(SALE_ID);
        verify(bankRepository, never()).findById(any());
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void testCreatePaymentWhenBankNotFound() {
        // Arrange
        NewPaymentRequestDTO requestDTO = new NewPaymentRequestDTO(
                SALE_ID, BANK_ID, PAYMENT_DATE, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, PAYMENT_AMOUNT);
        
        Sale sale = new Sale();
        sale.setId(SALE_ID);

        when(saleRepository.findById(SALE_ID)).thenReturn(Optional.of(sale));
        when(bankRepository.findById(BANK_ID)).thenReturn(Optional.empty());

        // Act & Assert
        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> serviceToTest.createPayment(requestDTO));

        assertEquals("Banco no encontrado", exception.getMessage());

        verify(saleRepository, times(1)).findById(SALE_ID);
        verify(bankRepository, times(1)).findById(BANK_ID);
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void testCreatePaymentWhenSaleAlreadyPaid() {
        // Arrange
        NewPaymentRequestDTO requestDTO = new NewPaymentRequestDTO(
                SALE_ID, BANK_ID, PAYMENT_DATE, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, PAYMENT_AMOUNT);
        
        Sale sale = new Sale();
        sale.setId(SALE_ID);
        sale.setPaymentStatus(PaymentStatus.PAGADO);
        
        Bank bank = new Bank();
        bank.setId(BANK_ID);

        when(saleRepository.findById(SALE_ID)).thenReturn(Optional.of(sale));
        when(bankRepository.findById(BANK_ID)).thenReturn(Optional.of(bank));

        // Act & Assert
        DuplicatedEntityException exception = assertThrows(
                DuplicatedEntityException.class,
                () -> serviceToTest.createPayment(requestDTO));

        assertEquals("La venta ya está pagada", exception.getMessage());

        verify(saleRepository, times(1)).findById(SALE_ID);
        verify(bankRepository, times(1)).findById(BANK_ID);
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void testCreatePaymentWhenAmountExceedsBalance() {
        // Arrange
        Double excessiveAmount = SALE_BALANCE + 100.0;
        NewPaymentRequestDTO requestDTO = new NewPaymentRequestDTO(
                SALE_ID, BANK_ID, PAYMENT_DATE, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, excessiveAmount);
        
        Sale sale = new Sale();
        sale.setId(SALE_ID);
        sale.setRemainingBalance(SALE_BALANCE);
        sale.setPaymentStatus(PaymentStatus.PENDIENTE);
        
        Bank bank = new Bank();
        bank.setId(BANK_ID);

        when(saleRepository.findById(SALE_ID)).thenReturn(Optional.of(sale));
        when(bankRepository.findById(BANK_ID)).thenReturn(Optional.of(bank));

        // Act & Assert
        assertThrows(
                PaymentExceedsSaleBalanceException.class,
                () -> serviceToTest.createPayment(requestDTO));

        verify(saleRepository, times(1)).findById(SALE_ID);
        verify(bankRepository, times(1)).findById(BANK_ID);
        verify(paymentRepository, never()).save(any());
    }

    @Test
    void testCreatePaymentCompletesSaleWhenBalanceIsZero() throws Exception {
        // Arrange
        NewPaymentRequestDTO requestDTO = new NewPaymentRequestDTO(
                SALE_ID, BANK_ID, PAYMENT_DATE, 
                ACCOUNT_NUMBER, TRANSACTION_NUMBER, SALE_BALANCE);
        
        Sale sale = new Sale();
        sale.setId(SALE_ID);
        sale.setRemainingBalance(SALE_BALANCE);
        sale.setPaymentStatus(PaymentStatus.PENDIENTE);
        
        Bank bank = new Bank();
        bank.setId(BANK_ID);
        
        Payment payment = new Payment();
        payment.setReceiptNumber(PAYMENT_ID);
        
        when(saleRepository.findById(SALE_ID)).thenReturn(Optional.of(sale));
        when(bankRepository.findById(BANK_ID)).thenReturn(Optional.of(bank));
        when(paymentMapper.toPayment(requestDTO)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(payment);

        // Act
        serviceToTest.createPayment(requestDTO);

        // Assert
        ArgumentCaptor<Sale> saleCaptor = ArgumentCaptor.forClass(Sale.class);
        verify(saleRepository).save(saleCaptor.capture());
        
        assertEquals(0.0, saleCaptor.getValue().getRemainingBalance());
        assertEquals(PaymentStatus.PAGADO, saleCaptor.getValue().getPaymentStatus());
        assertEquals(PAYMENT_DATE, saleCaptor.getValue().getPaymentDate());
    }
}