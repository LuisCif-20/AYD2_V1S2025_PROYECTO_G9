package com.ayd2.imporcomgua.controllers.warehouse;

import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.services.warehouse.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/inventory")
@RequiredArgsConstructor
// @PreAuthorize("hasAuthority('GERENTE_GENERAL')")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_INVENTARIO')")
    public ResponseEntity<List<Inventory>> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }
}
