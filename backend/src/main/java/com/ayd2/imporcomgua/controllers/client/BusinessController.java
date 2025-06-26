package com.ayd2.imporcomgua.controllers.client;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.services.client.BusinessService;

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
    
}