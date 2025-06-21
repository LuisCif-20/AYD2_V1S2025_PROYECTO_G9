package com.ayd2.imporcomgua.mappers.payment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ayd2.imporcomgua.dto.payment.NewPaymentRequestDTO;
import com.ayd2.imporcomgua.dto.payment.PaymentResponseDTO;
import com.ayd2.imporcomgua.mappers.sale.SaleMapper;
import com.ayd2.imporcomgua.models.payment.Payment;

@Mapper(componentModel = "spring", uses = { BankMapper.class, SaleMapper.class })
public interface PaymentMapper {
    PaymentResponseDTO toPaymentResponseDTO(Payment payment);

    @Mapping(target = "receiptNumber", ignore = true)
    @Mapping(target = "bank", ignore = true)
    @Mapping(target = "sale", ignore = true)
    Payment toPayment(NewPaymentRequestDTO paymentRequestDTO);
}
