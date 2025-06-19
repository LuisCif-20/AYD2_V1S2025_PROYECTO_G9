package com.ayd2.imporcomgua.services.products;

import com.ayd2.imporcomgua.dto.products.*;
import com.ayd2.imporcomgua.dto.generic.PagedResponseDTO;
import com.ayd2.imporcomgua.exceptions.*;

import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResponseDTO createProduct(NewProductRequestDTO productRequestDTO) throws DuplicatedEntityException;
    ProductResponseDTO updateProduct(String code, UpdateProductRequestDTO productRequestDTO) 
        throws NotFoundException, DuplicatedEntityException;
    ProductResponseDTO getProduct(String code) throws NotFoundException;
    PagedResponseDTO<ProductResponseDTO> getAllProducts(Pageable pageable);
    void deleteProduct(String code) throws NotFoundException;
}