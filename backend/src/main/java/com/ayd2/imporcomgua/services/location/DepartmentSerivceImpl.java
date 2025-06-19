package com.ayd2.imporcomgua.services.location;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.location.DepartmentResponseDTO;
import com.ayd2.imporcomgua.models.location.Department;
import com.ayd2.imporcomgua.repositories.location.DepartmentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class DepartmentSerivceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDTO getDepartment(String code) {
        final Department department = departmentRepository.findByCode(code)
                .orElseThrow(() -> );
    }

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
    }
    
}
