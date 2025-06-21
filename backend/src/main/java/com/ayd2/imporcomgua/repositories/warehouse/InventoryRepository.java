package com.ayd2.imporcomgua.repositories.warehouse;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.models.warehouse.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>, JpaSpecificationExecutor<Inventory> {
    Optional<Inventory> findByProduct(Product product);
}
