package com.ayd2.imporcomgua.dto.products;

import jakarta.validation.constraints.*;

public record UpdateProductRequestDTO(
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String name,

    Long presentationId,

    @Positive(message = "Las unidades por presentación deben ser mayores a 0")
    Integer unitsPerPresentation,

    @Positive(message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio debe tener máximo 8 dígitos enteros y 2 decimales")
    Double pricePerPresentation
) {}
