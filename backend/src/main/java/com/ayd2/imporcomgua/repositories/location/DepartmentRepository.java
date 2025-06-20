package com.ayd2.imporcomgua.repositories.location;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.location.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    
}
