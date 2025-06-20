package com.ayd2.imporcomgua.dto.sales;

import java.util.UUID;

import com.ayd2.imporcomgua.models.sale.SaleStatus;

public record SaleSearchRequestDTO(
    String clientName,
    UUID shipmentNumber,
    SaleStatus status
) {}
