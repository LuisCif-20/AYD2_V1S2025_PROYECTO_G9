package com.ayd2.imporcomgua.controllers.location;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.location.MunicipalityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/municipality")
@RequiredArgsConstructor
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @GetMapping("/code")
    public ResponseEntity<List<MunicipalityResponseDTO>> findAllMunicipalities(@PathVariable String code)
            throws NotFoundException {
        final List<MunicipalityResponseDTO> municipalities = municipalityService.findAllMunicipalities(code);
        return ResponseEntity.status(HttpStatus.OK).body(municipalities);
    }
    
}
