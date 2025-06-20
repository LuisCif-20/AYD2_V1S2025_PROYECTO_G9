package com.ayd2.imporcomgua.controllers.sale;

import com.ayd2.imporcomgua.dto.sales.NewSaleRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.dto.sales.SaleSearchRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.InvalidPaymentTypeException;
import com.ayd2.imporcomgua.exceptions.NoStockException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.sales.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1.0/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(
            @RequestBody @Valid NewSaleRequestDTO saleRequestDTO) throws DuplicatedEntityException, NotFoundException, InvalidPaymentTypeException, NoStockException {
        SaleResponseDTO response = saleService.createSale(saleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSale(
            @PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(saleService.getSale(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSales(@Valid SaleSearchRequestDTO saleSearchRequestDTO) {
        return ResponseEntity.ok(saleService.getAllSales(saleSearchRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(
            @PathVariable UUID id) throws NotFoundException {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}