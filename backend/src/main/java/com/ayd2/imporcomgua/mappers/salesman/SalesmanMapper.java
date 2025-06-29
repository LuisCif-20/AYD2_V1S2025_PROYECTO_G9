package com.ayd2.imporcomgua.mappers.salesman;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ayd2.imporcomgua.dto.salesman.NewSalesmanRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanResponseDTO;
import com.ayd2.imporcomgua.dto.salesman.UpdateSalesmanRequestDTO;
import com.ayd2.imporcomgua.models.salesman.Salesman;

@Mapper(componentModel = "spring")
public interface SalesmanMapper {

    SalesmanResponseDTO toSalesmanResponseDTO(Salesman salesman);

    @Mapping(target = "code", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    Salesman toSalesman(NewSalesmanRequestDTO newSalesmanRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "code", ignore = true)
    void updateSalesmanFromDTO(UpdateSalesmanRequestDTO updateSalesmanRequestDTO, @MappingTarget Salesman salesman);
    
}
