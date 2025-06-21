package com.ayd2.imporcomgua.dto.salesman;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateSalesmanRequestDTO(

    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String firstName,

    @Size(min = 2, max = 100, message = "El apellido debe tener entre 2 y 100 caracteres")
    String lastName,

    @Pattern(regexp = "^\\d{4}-\\d{4}$", message = "El teléfono debe tener el formato 1234-1234")
    String phone,

    @Size(max = 255, message = "La dirección no puede exceder los 255 caracteres")
    String address,

    @DecimalMin(value = "0.00", message = "La comisión no puede ser negativa")
    @DecimalMax(value = "0.99", message = "La comisión no puede ser mayor a 99%")
    Double commissionPercent

) {}
