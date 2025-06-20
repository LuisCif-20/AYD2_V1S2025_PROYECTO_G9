package com.ayd2.imporcomgua.services.products;

import com.ayd2.imporcomgua.dto.products.*;
import com.ayd2.imporcomgua.exceptions.*;

import java.util.List;


public interface ProductService {
    ProductResponseDTO createProduct(NewProductRequestDTO productRequestDTO) throws DuplicatedEntityException;
    ProductResponseDTO updateProduct(String code, UpdateProductRequestDTO productRequestDTO) 
        throws NotFoundException, DuplicatedEntityException;
    ProductResponseDTO getProduct(String code) throws NotFoundException;
    List<ProductResponseDTO> getAllProducts();
    void deleteProduct(String code) throws NotFoundException;
}