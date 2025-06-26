package com.ayd2.imporcomgua.mappers.client;

import org.mapstruct.Mapper;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.models.client.Business;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    BusinessResponseDTO toBusinessResponseDTO(Business business);
    
}
