package com.ayd2.imporcomgua.services.warehouse;

import com.ayd2.imporcomgua.dto.warehouse.NewProductWHEntryRequestDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHEntryResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotActivatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface ProductWHEntryService {
    ProductWHEntryResponseDTO createProductWHEntry(NewProductWHEntryRequestDTO dtoRequest) throws NotFoundException, NotActivatedEntityException;
}
