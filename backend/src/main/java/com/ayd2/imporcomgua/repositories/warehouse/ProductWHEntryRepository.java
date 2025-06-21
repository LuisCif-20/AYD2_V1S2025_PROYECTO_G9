package com.ayd2.imporcomgua.repositories.warehouse;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.warehouse.ProductWarehouseEntry;

public interface ProductWHEntryRepository extends JpaRepository<ProductWarehouseEntry, UUID>{
    
}
