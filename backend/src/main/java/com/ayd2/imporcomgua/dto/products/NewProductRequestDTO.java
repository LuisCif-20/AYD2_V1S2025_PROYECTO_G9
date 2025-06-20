package com.ayd2.imporcomgua.dto.products;

import jakarta.validation.constraints.*;

public record NewProductRequestDTO(
    @NotBlank(message = "El código del producto es obligatorio")
    @Pattern(regexp = "^[A-Z]{4}\\d{4}$", 
             message = "El código debe tener 4 letras mayúsculas seguidas de 4 dígitos (Ej: ABCD1234)")
    String code,

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    String name,

    @NotNull(message = "La presentación es obligatoria")
    Long presentationId,

    @NotNull(message = "Las unidades por presentación son obligatorias")
    @Positive(message = "Las unidades por presentación deben ser mayores a 0")
    Integer unitsPerPresentation,

    @NotNull(message = "El precio por presentación es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 10 dígitos enteros y 2 decimales")
    Double pricePerPresentation
) {}