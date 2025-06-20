package com.ayd2.imporcomgua.services.sales;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.sales.NewSaleRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    @Override
    public SaleResponseDTO createSale(NewSaleRequestDTO saleRequestDTO) throws DuplicatedEntityException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSale'");
    }

    @Override
    public SaleResponseDTO getSale(UUID id) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSale'");
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSales'");
    }

    @Override
    public void deleteSale(UUID id) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSale'");
    }

}
