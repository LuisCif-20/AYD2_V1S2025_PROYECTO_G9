package com.ayd2.imporcomgua.mappers.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ayd2.imporcomgua.dto.user.NewUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserResponseDTO;
import com.ayd2.imporcomgua.models.user.User;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserMapper {

    UserResponseDTO toUserResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User toUser(NewUserRequestDTO newUserRequestDTO);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void UpdateUserFromDTO(UpdateUserRequestDTO updateUserRequestDTO, @MappingTarget User user);

}
