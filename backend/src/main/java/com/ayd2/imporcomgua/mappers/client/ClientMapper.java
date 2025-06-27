package com.ayd2.imporcomgua.mappers.client;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ayd2.imporcomgua.dto.client.ClientResponseDTO;
import com.ayd2.imporcomgua.dto.client.NewClientRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateClientRequestDTO;
import com.ayd2.imporcomgua.mappers.location.MunicipalityMapper;
import com.ayd2.imporcomgua.models.client.Client;

@Mapper(componentModel = "spring", uses = { MunicipalityMapper.class, BusinessMapper.class })
public interface ClientMapper {
    
    ClientResponseDTO toClientResponseDTO(Client client);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "municipality", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "business", ignore = true)
    Client toClient(NewClientRequestDTO newClientRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "municipality", ignore = true)
    @Mapping(target = "business", ignore = true)
    void updateClientFromDTO(UpdateClientRequestDTO updateClientRequestDTO, @MappingTarget Client client);

}
