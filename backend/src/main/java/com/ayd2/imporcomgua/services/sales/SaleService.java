package com.ayd2.imporcomgua.services.sales;

import com.ayd2.imporcomgua.dto.sales.NewSaleRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    SaleResponseDTO createSale(NewSaleRequestDTO saleRequestDTO) throws DuplicatedEntityException;
    SaleResponseDTO getSale(UUID id) throws NotFoundException;
    List<SaleResponseDTO> getAllSales();
    void deleteSale(UUID id) throws NotFoundException;
}