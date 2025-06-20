package com.ayd2.imporcomgua.services.warehouse;

import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHOutputRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface ProductWHOutputService {
    SaleResponseDTO processProductWHOutput(ProductWHOutputRequestDTO outputRequestDTO) throws NotFoundException, DuplicatedEntityException;
}
