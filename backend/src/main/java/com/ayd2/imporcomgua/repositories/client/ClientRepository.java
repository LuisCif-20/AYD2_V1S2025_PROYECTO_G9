package com.ayd2.imporcomgua.repositories.client;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.client.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    
}
