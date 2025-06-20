package com.ayd2.imporcomgua.mappers.location;

import org.mapstruct.Mapper;

import com.ayd2.imporcomgua.dto.location.MunicipalityResponseDTO;
import com.ayd2.imporcomgua.models.location.Municipality;

@Mapper(componentModel = "spring")
public interface MunicipalityMapper {
    
    MunicipalityResponseDTO toMunicipalityResponseDTO(Municipality municipality);


}
