package com.ayd2.imporcomgua.dto.sales;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.ayd2.imporcomgua.models.client.SaleType;

public record NewSaleRequestDTO(
    @NotNull(message = "La fecha de venta es requerida")
    @PastOrPresent(message = "La fecha de venta no puede ser futura")
    LocalDate saleDate,

    @NotNull(message = "El cliente es requerido")
    @Positive(message = "El ID del cliente debe ser positivo")
    Long clientId,

    @NotNull(message = "El tipo de pago es requerido")
    SaleType paymentType,

    @NotNull(message = "Los días de crédito son requeridos")
    @Min(value = 0, message = "Los días de crédito no pueden ser negativos")
    Integer creditDays,

    @NotNull(message = "El vendedor es requerido")
    UUID salesmanCode,

    @Positive(message = "El número de factura DTE debe ser positivo")
    Integer dteInvoiceNumber,

    @Size(max = 100, message = "El nombre en factura no puede exceder los 100 caracteres")
    String invoiceName,

    @Pattern(regexp = "^\\d{9}$", message = "El NIT debe contener exactamente 9 dígitos numéricos")
    String invoiceNit,

    String notes,
    
    @NotEmpty(message = "La venta debe incluir al menos un detalle")
    List<@Valid NewSaleDetailRequestDTO> details
) {}