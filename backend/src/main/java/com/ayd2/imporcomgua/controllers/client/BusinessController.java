package com.ayd2.imporcomgua.controllers.client;

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

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.dto.client.NewBusinessRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateBusinessRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.client.BusinessService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/business")
@RequiredArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @GetMapping
    public ResponseEntity<List<BusinessResponseDTO>> getAllBusiness() {
        final List<BusinessResponseDTO> business = businessService.getAllBusiness();
        return ResponseEntity.status(HttpStatus.OK).body(business);
    }

    @PostMapping
    public ResponseEntity<BusinessResponseDTO> createBusiness(
            @RequestBody @Valid NewBusinessRequestDTO newBusinessRequestDTO) throws DuplicatedEntityException {
        final BusinessResponseDTO business = businessService.createBusiness(newBusinessRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(business);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> updateBusiness(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateBusinessRequestDTO updateBusinessRequestDTO)
            throws NotFoundException, DuplicatedEntityException {
        final BusinessResponseDTO business = businessService.updateBusiness(id, updateBusinessRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(business);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable UUID id) throws NotFoundException {
        businessService.deleteBusiness(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}