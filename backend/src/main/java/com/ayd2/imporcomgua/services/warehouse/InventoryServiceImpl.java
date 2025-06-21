package com.ayd2.imporcomgua.services.warehouse;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    // TODO Implement mappers
    @Override
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories;
    }

}
