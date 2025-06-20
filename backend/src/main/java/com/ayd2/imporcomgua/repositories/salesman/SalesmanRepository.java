package com.ayd2.imporcomgua.repositories.salesman;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ayd2.imporcomgua.models.salesman.Salesman;

public interface SalesmanRepository extends JpaRepository<Salesman, UUID>, JpaSpecificationExecutor<Salesman> {
    
}
