package com.ayd2.imporcomgua.controllers.product;

import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;
import com.ayd2.imporcomgua.services.products.PresentationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/presentations")
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @GetMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_INVENTARIO')")
    public ResponseEntity<List<PresentationResponseDTO>> getAllPresentations() {
        return ResponseEntity.ok(presentationService.getAllPresentations());
    }
}