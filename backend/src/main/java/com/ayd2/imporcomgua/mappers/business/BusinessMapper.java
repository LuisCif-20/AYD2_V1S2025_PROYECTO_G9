package com.ayd2.imporcomgua.mappers.business;

import org.mapstruct.Mapper;

import com.ayd2.imporcomgua.dto.business.BusinessResponseDTO;
import com.ayd2.imporcomgua.models.business.Business;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    BusinessResponseDTO toBusinessResponseDTO(Business business);
    
}
