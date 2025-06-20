package com.ayd2.imporcomgua.services.products;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.generic.PagedResponseDTO;
import com.ayd2.imporcomgua.dto.products.NewProductRequestDTO;
import com.ayd2.imporcomgua.dto.products.ProductResponseDTO;
import com.ayd2.imporcomgua.dto.products.UpdateProductRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

@Service
public class ProductServiceImpl implements ProductService{

    @Override
    public ProductResponseDTO createProduct(NewProductRequestDTO productRequestDTO) throws DuplicatedEntityException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
    }

    @Override
    public ProductResponseDTO updateProduct(String code, UpdateProductRequestDTO productRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public ProductResponseDTO getProduct(String code) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProduct'");
    }

    @Override
    public PagedResponseDTO<ProductResponseDTO> getAllProducts(Pageable pageable) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProducts'");
    }

    @Override
    public void deleteProduct(String code) throws NotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }
    
}
