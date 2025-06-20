package com.ayd2.imporcomgua.dto.warehouse;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record ProductWHOutputRequestDTO(
    @NotNull(message = "El ID de la venta es obligatorio")
    UUID saleId,

    @NotNull(message = "La fecha de salida es obligatoria")
    @PastOrPresent(message = "La fecha de salida no puede ser futura")
    LocalDate exitDate
) {}
