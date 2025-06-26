package com.ayd2.imporcomgua.mappers.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.dto.client.NewBusinessRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateBusinessRequestDTO;
import com.ayd2.imporcomgua.models.client.Business;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    BusinessResponseDTO toBusinessResponseDTO(Business business);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    Business toBusiness(NewBusinessRequestDTO newBusinessRequestDTO);

    @Mapping(target = "id", ignore = true)
    void UpdateBusinessFromDTO(
            UpdateBusinessRequestDTO updateBusinessRequestDTO,
            @MappingTarget Business business);
    
}
