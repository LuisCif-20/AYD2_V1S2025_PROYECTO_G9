package com.ayd2.imporcomgua.services.location;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.location.DepartmentResponseDTO;
import com.ayd2.imporcomgua.mappers.location.DepartmentMapper;
import com.ayd2.imporcomgua.repositories.location.DepartmentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class DepartmentSerivceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponseDTO> getAllDepartments() {
        final List<DepartmentResponseDTO> departments = departmentRepository.findAll()
                .stream()
                .map(department -> departmentMapper.toDepartmentResponseDTO(department))
                .toList();
        return departments;
    }
    
}
