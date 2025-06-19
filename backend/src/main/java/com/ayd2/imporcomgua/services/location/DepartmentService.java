package com.ayd2.imporcomgua.services.location;

import java.util.List;

import com.ayd2.imporcomgua.dto.location.DepartmentResponseDTO;

public interface DepartmentService {
    
    DepartmentResponseDTO getDepartment(String code);

    List<DepartmentResponseDTO> getAllDepartments();

}
