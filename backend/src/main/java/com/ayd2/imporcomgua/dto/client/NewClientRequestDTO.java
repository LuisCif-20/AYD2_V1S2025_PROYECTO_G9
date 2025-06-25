package com.ayd2.imporcomgua.dto.client;

import java.util.UUID;

import com.ayd2.imporcomgua.models.client.SaleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewClientRequestDTO(
    
    @NotBlank(message = "El nombre del contacto es obligatorio")
    @Size(max = 100, message = "El nombre de contacto debe ser menor a 100 caracteres")
    String contactName,

    @NotBlank(message = "El codigo de municipio es obligatorio")
    @Size(min = 4, max = 4, message = "El codigo del municio debe ser de 4 caracteres")
    String municipalityCode,
    
    @NotNull(message = "El tipo de venta es obligatorio")
    SaleType saleType,

    UUID businessId,

    @Size(max = 255, message = "La direccion debe ser menor a 255 caracteres")
    String address,

    @Pattern(regexp = "^\\d{9}$", message = "El nit debe ser de 9 digitos exactos")
    String nit,

    @Size(max = 100, message = "El nombre del encargado de almacen debe ser menor a 100 caracteres")
    String warehouseManager,

    @Pattern(regexp = "^\\d{4}-\\d{4}$", message = "El numero de telefono debe tener el formato ####-####")
    String phone,

    String notes

) {}
