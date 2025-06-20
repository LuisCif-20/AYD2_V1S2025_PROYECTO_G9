package com.ayd2.imporcomgua.services.location;

import java.util.List;

import com.ayd2.imporcomgua.dto.location.DepartmentResponseDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface DepartmentService {
    
    DepartmentResponseDTO getDepartment(String code) throws NotFoundException;

    List<DepartmentResponseDTO> getAllDepartments();

}
