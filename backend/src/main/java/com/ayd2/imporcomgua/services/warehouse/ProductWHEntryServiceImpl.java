package com.ayd2.imporcomgua.services.warehouse;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.warehouse.NewProductWHEntryRequestDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHEntryResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.warehouse.ProductWHEntryMapper;
import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.models.warehouse.ProductWarehouseEntry;
import com.ayd2.imporcomgua.repositories.product.ProductRepository;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;
import com.ayd2.imporcomgua.repositories.warehouse.ProductWHEntryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductWHEntryServiceImpl implements ProductWHEntryService {
    private final ProductWHEntryRepository productWHEntryRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final ProductWHEntryMapper productWHEntryMapper;
    
    @Override
    public ProductWHEntryResponseDTO createProductWHEntry(NewProductWHEntryRequestDTO dtoRequest)
            throws NotFoundException {

        Product product = productRepository.findById(dtoRequest.productCode())
                .orElseThrow(
                        () -> new NotFoundException("Producto no encontrado con código: " + dtoRequest.productCode()));

        ProductWarehouseEntry entry = productWHEntryMapper.toProductWarehouseEntry(dtoRequest);

        entry.setProduct(product);

        // Actualizar inventario
        Inventory inventory = inventoryRepository.findByProduct(product)
                .orElseThrow(() -> new NotFoundException("Inventario no encontrado para el producto: " + product.getCode()));

        // Actualizar cantidades
        inventory.setTotalQuantity(inventory.getTotalQuantity() + dtoRequest.quantityPresentation());
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + dtoRequest.quantityPresentation());
        inventory.setLastUpdated(LocalDate.now());

        inventoryRepository.save(inventory);
        ProductWarehouseEntry savedEntry = productWHEntryRepository.save(entry);

        return productWHEntryMapper.toProductWHEntryResponseDTO(savedEntry);
    }

}
