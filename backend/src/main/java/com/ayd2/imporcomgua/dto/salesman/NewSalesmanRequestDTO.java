package com.ayd2.imporcomgua.dto.salesman;

import jakarta.validation.constraints.*;

public record NewSalesmanRequestDTO(
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String firstName,

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    String lastName,

    @Pattern(regexp = "^\\d{4}-\\d{4}$", message = "El teléfono debe tener el formato 1234-1234")
    String phone,

    @Size(max = 255, message = "La dirección no puede exceder los 255 caracteres")
    String address,

    @NotNull(message = "El porcentaje de comisión es obligatorio")
    @DecimalMin(value = "0.00", message = "La comisión no puede ser negativa")
    @DecimalMax(value = "0.99", message = "La comisión no puede ser mayor a 99%")
    Double commissionPercent
) {}