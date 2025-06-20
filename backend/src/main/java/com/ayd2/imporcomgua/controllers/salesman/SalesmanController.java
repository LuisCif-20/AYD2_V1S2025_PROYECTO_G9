package com.ayd2.imporcomgua.controllers.salesman;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.salesman.NewSalesmanRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanResponseDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanSearchRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.UpdateSalesmanRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.salesman.SalesmanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/salesmans")
@RequiredArgsConstructor
public class SalesmanController {

    private final SalesmanService salesmanService;

    @GetMapping("/id")
    public ResponseEntity<SalesmanResponseDTO> getSalesman(@PathVariable UUID id) throws NotFoundException {
        final SalesmanResponseDTO salesmanResponseDTO = salesmanService.getSalesman(id);
        return ResponseEntity.status(HttpStatus.OK).body(salesmanResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SalesmanResponseDTO>> getAllSalesmans(
            @Valid SalesmanSearchRequestDTO salesmanSearchRequestDTO) {
        final List<SalesmanResponseDTO> salesmans = salesmanService.getAllSalesmans(salesmanSearchRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(salesmans);
    }

    @PostMapping
    public ResponseEntity<SalesmanResponseDTO> createSalesman(
            @RequestBody @Valid NewSalesmanRequestDTO newSalesmanRequestDTO) {
        final SalesmanResponseDTO salesmanResponseDTO = salesmanService.createSalesman(newSalesmanRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salesmanResponseDTO);
    }

    @PatchMapping("/id")
    public ResponseEntity<SalesmanResponseDTO> updateSalesman(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateSalesmanRequestDTO updateSalesmanRequestDTO)
            throws NotFoundException {
        final SalesmanResponseDTO salesmanResponseDTO = salesmanService.updateSalesmanResponseDTO(id, updateSalesmanRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(salesmanResponseDTO);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteSalesman(@PathVariable UUID id) throws NotFoundException {
        salesmanService.deleteSalesman(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
