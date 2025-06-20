package com.ayd2.imporcomgua.repositories.location;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ayd2.imporcomgua.models.location.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, String>, JpaSpecificationExecutor<Municipality> {

    Optional<Municipality> findByCode(String code);

}
