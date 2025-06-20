package com.ayd2.imporcomgua.dto.client;

import com.ayd2.imporcomgua.models.client.SaleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewClientRequestDTO(
    
    @NotBlank(message = "El nombre del contacto es obligatorio")
    @Size(max = 100, message = "El nombre de contacto debe ser menor a 100 caracteres")
    String contactName,

    @NotBlank(message = "El codigo de municipio es obligatorio")
    @Size(min = 4, max = 4, message = "El codigo del municio debe ser de 4 caracteres")
    String municipalityCode,
    
    @NotNull(message = "El tipo de venta es obligatorio")
    @Size(min = 7, message = "El tipo de venta debe ser menor a 7 caracteres.")
    SaleType saleType,

    @Size(max = 100, message = "El nombre de negocio debe ser menor a 100 caracteres.")
    String businessName,

    @Size(max = 255, message = "La direccion debe ser menor a 255 caracteres.")
    String address,

    @Size(min=9, max = 9, message = "El nit debe ser de 9 caracteres exactos.")
    String nit,

    @Size(max = 100, message = "El nombre del encargado de almacen debe ser menor a 100 caracteres.")
    String warehouseManager,

    @Size(min=9, max = 9, message = "El numer de telefono debe ser de 9 caracteres exactos.")
    String phone,

    String notes

) {}
