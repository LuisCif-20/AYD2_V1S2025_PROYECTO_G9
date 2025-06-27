package com.ayd2.imporcomgua.controllers.location;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.location.DepartmentResponseDTO;
import com.ayd2.imporcomgua.services.location.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS')")
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        final List<DepartmentResponseDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.status(HttpStatus.OK).body(departments);
    }
    
}
