package com.ayd2.imporcomgua.dto.payment;

import java.util.UUID;

import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;

public record PaymentResponseDTO(
    UUID receipt_number,
    SaleResponseDTO sale,
    BankResponseDTO bank,
    String accountNumber,
    String transactionNumber,
    Double amount
) {}
