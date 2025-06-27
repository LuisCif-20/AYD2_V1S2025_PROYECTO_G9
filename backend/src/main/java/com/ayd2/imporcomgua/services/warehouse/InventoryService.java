package com.ayd2.imporcomgua.services.warehouse;

import java.util.List;

import com.ayd2.imporcomgua.dto.warehouse.UpdateInventoryRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotActivatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.models.warehouse.Inventory;

public interface InventoryService {
    
    List<Inventory> getAllInventories();
    Inventory updateLowStockThreshold(Long inventoryId, UpdateInventoryRequestDTO updateRequest) throws NotFoundException, NotActivatedEntityException;
}
