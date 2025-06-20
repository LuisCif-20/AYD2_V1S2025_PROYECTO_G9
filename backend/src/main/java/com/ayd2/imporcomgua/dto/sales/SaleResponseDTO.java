package com.ayd2.imporcomgua.dto.sales;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.ayd2.imporcomgua.models.client.Client;
import com.ayd2.imporcomgua.models.sale.PaymentStatus;
import com.ayd2.imporcomgua.models.sale.PaymentType;
import com.ayd2.imporcomgua.models.sale.SaleStatus;
import com.ayd2.imporcomgua.models.salesman.Salesman;

public record SaleResponseDTO(
    UUID id,
    LocalDate saleDate,
    LocalDate warehouseExitDate,
    Client client,
    UUID shipmentNumber,
    PaymentType paymentType,
    Integer creditDays,
    Salesman salesman,
    Integer dteInvoiceNumber,
    String invoiceName,
    String invoiceNit,
    Double total,
    PaymentStatus paymentStatus,
    SaleStatus saleStatus,
    LocalDate paymentDate,
    String notes,
    List<SaleDetailResponseDTO> details
) {}
