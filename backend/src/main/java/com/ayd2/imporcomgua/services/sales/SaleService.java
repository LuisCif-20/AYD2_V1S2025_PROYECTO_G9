package com.ayd2.imporcomgua.services.sales;

import com.ayd2.imporcomgua.dto.sales.NewSaleRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.dto.sales.SaleSearchRequestDTO;
import com.ayd2.imporcomgua.exceptions.InvalidPaymentTypeException;
import com.ayd2.imporcomgua.exceptions.NoStockException;
import com.ayd2.imporcomgua.exceptions.NotActivatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface SaleService {
    SaleResponseDTO createSale(NewSaleRequestDTO saleRequestDTO) throws NotFoundException, InvalidPaymentTypeException, NoStockException, NotActivatedEntityException;
    SaleResponseDTO getSale(UUID id) throws NotFoundException;
    List<SaleResponseDTO> getAllSales(SaleSearchRequestDTO saleSearchRequestDTO);
    void deleteSale(UUID id) throws NotFoundException;
}