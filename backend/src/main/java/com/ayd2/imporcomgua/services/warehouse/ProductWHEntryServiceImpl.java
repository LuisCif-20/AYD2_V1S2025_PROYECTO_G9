package com.ayd2.imporcomgua.services.warehouse;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.warehouse.NewProductWHEntryRequestDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHEntryResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

@Service
public class ProductWHEntryServiceImpl implements ProductWHEntryService{

    @Override
    public ProductWHEntryResponseDTO createProductWHEntry(NewProductWHEntryRequestDTO dtoRequest)
            throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProductWHEntry'");
    }
    
}
