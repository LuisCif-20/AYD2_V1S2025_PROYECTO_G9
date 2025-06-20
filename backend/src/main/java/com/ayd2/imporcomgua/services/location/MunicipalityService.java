package com.ayd2.imporcomgua.services.location;

import java.util.List;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.dto.location.MunicipalitySearchRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface MunicipalityService {

    List<MunicipalityResponseDTO> findAllMunicipalities(MunicipalitySearchRequestDTO municipalitySearchRequestDTO)
            throws NotFoundException;

}
