package com.ayd2.imporcomgua.dto.products;

import jakarta.validation.constraints.*;

public record UpdateProductRequestDTO(
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String name,

    Long presentationId,

    @Positive(message = "Las unidades por presentación deben ser mayores a 0")
    Integer unitsPerPresentation,

    @Positive(message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 dígitos enteros y 2 decimales")
    Double pricePerPresentation
) {}
