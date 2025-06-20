package com.ayd2.imporcomgua.services.location;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.location.MunicipalityMapper;
import com.ayd2.imporcomgua.repositories.location.DepartmentRepository;
import com.ayd2.imporcomgua.repositories.location.MunicipalityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MunicipalityServiceImpl implements MunicipalityService {

    private final MunicipalityRepository municipalityRepository;
    private final DepartmentRepository departmentRepository;
    private final MunicipalityMapper municipalityMapper;

    @Override
    public List<MunicipalityResponseDTO> findAllMunicipalities(String code) throws NotFoundException {
        if (!departmentRepository.existByCode(code))
                throw new NotFoundException("Departamento con codigo " + code + " no existe.");
        final List<MunicipalityResponseDTO> municipalities = municipalityRepository.findByDepartmentCode(code)
                .stream()
                .map(municipality -> municipalityMapper.toMunicipalityResponseDTO(municipality))
                .toList();
        return municipalities;
    }
    
}
