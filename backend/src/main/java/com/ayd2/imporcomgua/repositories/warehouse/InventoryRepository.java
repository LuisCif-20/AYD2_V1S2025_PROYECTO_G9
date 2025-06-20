package com.ayd2.imporcomgua.repositories.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.models.warehouse.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Product>{
    
}
