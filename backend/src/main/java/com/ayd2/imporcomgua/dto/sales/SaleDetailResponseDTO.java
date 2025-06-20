package com.ayd2.imporcomgua.dto.sales;

public record SaleDetailResponseDTO(
    String productName,
    Integer quantity,
    Integer unitQuantity,
    Double pricePerPresentation,
    Double subtotal
) {}
