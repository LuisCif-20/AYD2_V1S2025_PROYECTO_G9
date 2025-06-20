package com.ayd2.imporcomgua.controllers.product;

import com.ayd2.imporcomgua.dto.generic.PagedResponseDTO;
import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;
import com.ayd2.imporcomgua.services.products.PresentationService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/presentations")
@RequiredArgsConstructor
public class PresentationController {

    private final PresentationService presentationService;

    @GetMapping
    public ResponseEntity<PagedResponseDTO<PresentationResponseDTO>> getAllPresentations(
            @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(presentationService.getAllPresentations(pageable));
    }
}