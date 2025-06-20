package com.ayd2.imporcomgua.repositories.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.location.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, String> {

    List<Municipality> findByDepartmentCode(String code);
    
}
