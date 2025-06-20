package com.ayd2.imporcomgua.controllers.location;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.dto.location.MunicipalitySearchRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.location.MunicipalityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/municipalities")
@RequiredArgsConstructor
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @GetMapping
    public ResponseEntity<List<MunicipalityResponseDTO>> findAllMunicipalities(
            @Valid MunicipalitySearchRequestDTO municipalitySearchRequestDTO)
            throws NotFoundException {
        final List<MunicipalityResponseDTO> municipalities = municipalityService
                .findAllMunicipalities(municipalitySearchRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(municipalities);
    }

}
