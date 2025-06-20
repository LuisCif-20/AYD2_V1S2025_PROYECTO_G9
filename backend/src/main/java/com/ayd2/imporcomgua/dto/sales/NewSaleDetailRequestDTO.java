package com.ayd2.imporcomgua.dto.sales;

import jakarta.validation.constraints.*;

public record NewSaleDetailRequestDTO(
    @NotBlank(message = "El código de producto es obligatorio")
    @Pattern(regexp = "^[A-Z]{4}\\d{4}$", 
             message = "El código debe tener 4 letras mayúsculas seguidas de 4 dígitos (Ej: ABCD1234)")
    String productCode,

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    Integer quantity,

    @NotNull(message = "Las unidades son obligatorias")
    @Positive(message = "Las unidades deben ser mayores a 0")
    Integer unitQuantity,

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio debe tener máximo 8 dígitos enteros y 2 decimales")
    Double pricePerPresentation
) {}