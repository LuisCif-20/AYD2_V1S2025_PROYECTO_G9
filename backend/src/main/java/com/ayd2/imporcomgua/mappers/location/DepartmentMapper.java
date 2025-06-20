package com.ayd2.imporcomgua.mappers.location;

import org.mapstruct.Mapper;

import com.ayd2.imporcomgua.dto.location.DepartmentResponseDTO;
import com.ayd2.imporcomgua.models.location.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    
    DepartmentResponseDTO toDepartmentResponseDTO(Department department);

}
