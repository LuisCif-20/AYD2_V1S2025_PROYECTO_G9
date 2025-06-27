package com.ayd2.imporcomgua.mappers.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ayd2.imporcomgua.dto.user.NewUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UpdateUserAccountRequestDTO;
import com.ayd2.imporcomgua.dto.user.UserAccountResponseDTO;
import com.ayd2.imporcomgua.models.user.UserAccount;

@Mapper(componentModel = "spring", uses = { RoleMapper.class })
public interface UserAccountMapper {

    UserAccountResponseDTO toUserAccountResponseDTO(UserAccount userAccount);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    UserAccount toUserAccount(NewUserAccountRequestDTO newUserRequestDTO);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    void UpdateUserAccountFromDTO(
            UpdateUserAccountRequestDTO updateUserAccountRequestDTO,
            @MappingTarget UserAccount userAccount);

}
