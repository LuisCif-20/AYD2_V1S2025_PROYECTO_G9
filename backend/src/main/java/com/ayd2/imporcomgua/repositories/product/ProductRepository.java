package com.ayd2.imporcomgua.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, String code);
}
