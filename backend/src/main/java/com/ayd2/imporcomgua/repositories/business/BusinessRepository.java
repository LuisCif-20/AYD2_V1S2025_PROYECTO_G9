package com.ayd2.imporcomgua.repositories.business;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.business.Business;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
          
}
