package com.ayd2.imporcomgua.services.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.user.RoleResponseDTO;
import com.ayd2.imporcomgua.mappers.user.RoleMapper;
import com.ayd2.imporcomgua.repositories.user.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public List<RoleResponseDTO> getAllRoles() {
        final List<RoleResponseDTO> roles = roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponseDTO)
                .toList();
        return roles;
    }
    
}
