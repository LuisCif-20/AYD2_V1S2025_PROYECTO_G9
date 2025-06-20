package com.ayd2.imporcomgua.repositories.product;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.product.Presentation;

public interface PresentationRepository extends JpaRepository<Presentation, Long> {
    
}
