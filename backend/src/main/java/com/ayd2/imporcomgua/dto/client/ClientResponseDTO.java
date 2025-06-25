package com.ayd2.imporcomgua.dto.client;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.models.client.SaleType;

public record ClientResponseDTO(
    Long id,
    String code,
    String contactName,
    String businessName,
    MunicipalityResponseDTO municipality,
    String address,
    String nit,
    String warehouseManager,
    String phone,
    SaleType saleType,
    String notes,
    Boolean isActive
) { }
