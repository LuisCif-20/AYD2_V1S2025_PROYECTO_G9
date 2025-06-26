package com.ayd2.imporcomgua.mappers.user;

import org.mapstruct.Mapper;

import com.ayd2.imporcomgua.dto.user.RoleResponseDTO;
import com.ayd2.imporcomgua.models.user.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDTO toRoleResponseDTO(Role role);
    
}
