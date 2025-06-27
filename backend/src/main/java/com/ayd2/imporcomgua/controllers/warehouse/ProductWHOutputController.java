package com.ayd2.imporcomgua.controllers.warehouse;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHOutputRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.warehouse.ProductWHOutputService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/warehouse-outputs")
@RequiredArgsConstructor
// @PreAuthorize("hasAuthority('GERENTE_GENERAL')")
public class ProductWHOutputController {
    private final ProductWHOutputService productWHOutputService;
    
    @PutMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_INVENTARIO')")
    public ResponseEntity<SaleResponseDTO> updateProductWHOutput(
            @RequestBody @Valid ProductWHOutputRequestDTO dtoRequest) throws NotFoundException, DuplicatedEntityException {
        SaleResponseDTO response = productWHOutputService.processProductWHOutput(dtoRequest);
        return ResponseEntity.ok(response);
    }
}
