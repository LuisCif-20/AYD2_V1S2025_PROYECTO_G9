package com.ayd2.imporcomgua.services.warehouse;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.warehouse.UpdateInventoryRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotActivatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.warehouse.InventoryMapper;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public List<Inventory> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories;
    }

    @Override
    public Inventory updateLowStockThreshold(Long inventoryId, UpdateInventoryRequestDTO updateRequest)
            throws NotFoundException, NotActivatedEntityException {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new NotFoundException("Inventario no encontrado con ID: " + inventoryId));
      if (inventory.getProduct().getIsActive() == null || !inventory.getProduct().getIsActive()) {
                throw new NotActivatedEntityException(
                                String.format("El producto %s (Código: %s) se encuentra descontinuado.",
                                                inventory.getProduct().getName(),
                                                inventory.getProduct().getCode()));
        }

        inventoryMapper.updateInventoryFromDTO(updateRequest, inventory);
        Inventory updatedInventory = inventoryRepository.save(inventory);
        return updatedInventory;
    }
}
