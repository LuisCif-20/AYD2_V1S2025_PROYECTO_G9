package com.ayd2.imporcomgua.dto.warehouse;

import jakarta.validation.constraints.Positive;

public record UpdateInventoryRequestDTO(
    @Positive(message = "El umbral de bajo stock debe ser un número positivo")
    Integer lowStockThreshold
) {}
