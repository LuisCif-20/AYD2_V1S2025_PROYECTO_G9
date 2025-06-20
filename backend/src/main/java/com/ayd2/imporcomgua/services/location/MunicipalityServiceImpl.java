package com.ayd2.imporcomgua.services.location;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.dto.location.MunicipalitySearchRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.location.MunicipalityMapper;
import com.ayd2.imporcomgua.models.location.Municipality;
import com.ayd2.imporcomgua.repositories.location.MunicipalityRepository;
import com.ayd2.imporcomgua.specifications.location.MunicipalitySpecs;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final MunicipalityMapper municipalityMapper;

    @Override
    public List<MunicipalityResponseDTO> findAllMunicipalities(MunicipalitySearchRequestDTO municipalitySearchRequestDTO)
            throws NotFoundException {
        final String code = municipalitySearchRequestDTO.department();
        Specification<Municipality> spec = MunicipalitySpecs.hasDepartmentCode(code);
        final List<MunicipalityResponseDTO> municipalities = municipalityRepository.findAll(spec)
                .stream()
                .map(municipalityMapper::toMunicipalityResponseDTO)
                .toList();
        return municipalities;
    }
    
}
