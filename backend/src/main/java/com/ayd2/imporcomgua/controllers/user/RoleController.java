package com.ayd2.imporcomgua.controllers.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.user.RoleResponseDTO;
import com.ayd2.imporcomgua.services.user.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/roles")
@RequiredArgsConstructor
// @PreAuthorize("hasAuthority('GERENTE_GENERAL')")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAllRoles() {
        final List<RoleResponseDTO> roles = roleService.getAllRoles();
        return ResponseEntity.status(HttpStatus.OK).body(roles); 
    }
    
}
