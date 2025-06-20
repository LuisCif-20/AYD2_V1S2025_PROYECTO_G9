package com.ayd2.imporcomgua.services.products;

import java.util.List;

import com.ayd2.imporcomgua.dto.products.NewProductRequestDTO;
import com.ayd2.imporcomgua.dto.products.ProductResponseDTO;
import com.ayd2.imporcomgua.dto.products.UpdateProductRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.product.ProductMapper;
import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.repositories.product.ProductRepository;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository = null;
    private final ProductMapper productMapper = null;

    @Override
    public ProductResponseDTO createProduct(NewProductRequestDTO productRequestDTO) throws DuplicatedEntityException {
        
        if (productRepository.existsByName(productRequestDTO.name())) {
            throw new DuplicatedEntityException(
                    "Un producto con el nombre '" + productRequestDTO.name() + "' ya existe.");
        }

        Product product = productMapper.toProduct(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toProductResponseDTO(savedProduct);
    }

    @Override
    public ProductResponseDTO updateProduct(String code, UpdateProductRequestDTO productRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        Product existingproduct = productRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado por el código: " + code));

        if (productRepository.existsByNameAndIdNot(productRequestDTO.name(), code)) {
            throw new DuplicatedEntityException(
                    "Ya existe un producto con el nombre '" + productRequestDTO.name()+".");
        }

        productMapper.updateProductFromDTO(productRequestDTO, existingproduct);
        Product updatedproduct = productRepository.save(existingproduct);
        return productMapper.toProductResponseDTO(updatedproduct);
    }


    @Override
    public ProductResponseDTO getProduct(String code) throws NotFoundException {
        Product product = productRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con el código: " + code));
        return productMapper.toProductResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                       .map(productMapper::toProductResponseDTO)
                       .toList(); 
    }

    @Override
    public void deleteProduct(String code) throws NotFoundException {
         Product product = productRepository.findById(code)
                .orElseThrow(() -> new NotFoundException("Producto no encotrado con el código: " + code));
        productRepository.delete(product);
    }
    





    
}
