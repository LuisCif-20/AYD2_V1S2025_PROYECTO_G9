package com.ayd2.imporcomgua.dto.payment;

import java.time.LocalDate;
import java.util.UUID;


import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;

public record PaymentResponseDTO(
    UUID receiptNumber,
    SaleResponseDTO sale,
    BankResponseDTO bank,
    String accountNumber,
    String transactionNumber,
    Double amount,
    LocalDate paymentDate
) {}
