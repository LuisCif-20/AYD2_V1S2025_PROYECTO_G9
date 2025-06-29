package com.ayd2.imporcomgua.dto.client;

import java.util.UUID;

import com.ayd2.imporcomgua.models.client.SaleType;

import jakarta.validation.constraints.Size;

public record UpdateClientRequestDTO(
    @Size(max = 100, message = "El nombre de contacto debe ser menor a 100 caracteres")
    String contactName,

    SaleType saleType,

    UUID businessId,

    @Size(max = 255, message = "La direccion debe ser menor a 255 caracteres")
    String address,

    @Size(min=9, max = 9, message = "El nit debe ser de 9 caracteres exactos")
    String nit,

    @Size(max = 100, message = "El nombre del encargado de almacen debe ser menor a 100 caracteres")
    String warehouseManager,

    @Size(min=9, max = 9, message = "El numer de telefono debe ser de 9 caracteres exactos")
    String phone,

    String notes,

    Boolean isActive

) { }
