package com.ayd2.imporcomgua.controllers.product;

import com.ayd2.imporcomgua.dto.products.*;
import com.ayd2.imporcomgua.exceptions.*;
import com.ayd2.imporcomgua.services.products.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1.0/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid NewProductRequestDTO productRequestDTO) 
            throws NotFoundException, DuplicatedEntityException {
        ProductResponseDTO responseDTO = productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/{code}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable String code,
            @RequestBody @Valid UpdateProductRequestDTO productRequestDTO) 
            throws NotFoundException, DuplicatedEntityException {
        ProductResponseDTO responseDTO = productService.updateProduct(code, productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable String code) throws NotFoundException {
        ProductResponseDTO responseDTO = productService.getProduct(code);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@Valid ProductSearchRequestDTO productSearchRequestDTO) {
        return ResponseEntity.ok(productService.getAllProducts(productSearchRequestDTO));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String code) throws NotFoundException {
        productService.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }
}