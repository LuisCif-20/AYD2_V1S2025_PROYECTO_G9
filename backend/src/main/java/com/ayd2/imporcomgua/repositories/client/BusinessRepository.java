package com.ayd2.imporcomgua.repositories.client;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.client.Business;

public interface BusinessRepository extends JpaRepository<Business, UUID> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, UUID id);
          
}
