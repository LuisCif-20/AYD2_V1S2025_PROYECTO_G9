package com.ayd2.imporcomgua.dto.warehouse;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NewProductWHEntryRequestDTO(
    @NotBlank(message = "El código de producto es obligatorio")
    @Pattern(regexp = "^[A-Z]{4}\\d{4}$", 
             message = "El código debe tener 4 letras mayúsculas seguidas de 4 dígitos (Ej: ABCD1234)")
    String productCode,

    @NotNull(message = "La cantidad de presentaciones es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a cero")
    Integer quantityPresentation,

    @NotNull(message = "Las unidades por presentación son obligatorias")
    @Positive(message = "Las unidades deben ser mayores a cero")
    Integer unitsPerPresentation,

    @NotBlank(message = "El número de contenedor es obligatorio")
    @Size(max = 10, message = "El número de contenedor no puede exceder 10 caracteres")
    String containerNumber,

    @NotBlank(message = "El número de DUCA es obligatorio")
    @Size(max = 10, message = "El número de DUCA no puede exceder 10 caracteres")
    String ducaNumber,

    @NotNull(message = "La fecha de DUCA es obligatoria")
    @PastOrPresent(message = "La fecha de DUCA no puede ser futura")
    LocalDate ducaDate,

    @Size(max = 10, message = "El número de DUCA rectificada no puede exceder 10 caracteres")
    String rectifiedDucaNumber,

    @PastOrPresent(message = "La fecha de DUCA rectificada no puede ser futura")
    LocalDate rectifiedDucaDate,

    String notes
) {}