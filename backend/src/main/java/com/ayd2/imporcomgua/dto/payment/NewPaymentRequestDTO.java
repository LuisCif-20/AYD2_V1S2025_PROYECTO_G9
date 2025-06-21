package com.ayd2.imporcomgua.dto.payment;

import jakarta.validation.constraints.*;
import java.util.UUID;

public record NewPaymentRequestDTO(
    @NotNull(message = "El ID de la venta es obligatorio")
    UUID saleId,

    @NotNull(message = "El ID del banco es obligatorio")
    UUID bankId,

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Size(min = 1, max = 15, message = "El número de cuenta debe tener entre 1 y 15 caracteres")
    String accountNumber,

    @NotBlank(message = "El número de transacción es obligatorio")
    @Size(min = 1, max = 15, message = "El número de transacción debe tener entre 1 y 15 caracteres")
    String transactionNumber,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El monto debe tener máximo 8 dígitos enteros y 2 decimales")
    Double amount
) {}