package com.ayd2.imporcomgua.controllers.warehouse;

import com.ayd2.imporcomgua.dto.warehouse.NewProductWHEntryRequestDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHEntryResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotActivatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.warehouse.ProductWHEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1.0/warehouse-entries")
@RequiredArgsConstructor
// @PreAuthorize("hasAuthority('GERENTE_GENERAL')")
public class ProductWHEntryController {

    private final ProductWHEntryService productWHEntryService;

    @PostMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_INVENTARIO')")
    public ResponseEntity<ProductWHEntryResponseDTO> createProductWHEntry(
            @RequestBody @Valid NewProductWHEntryRequestDTO dtoRequest) throws NotFoundException, NotActivatedEntityException {
        ProductWHEntryResponseDTO response = productWHEntryService.createProductWHEntry(dtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}