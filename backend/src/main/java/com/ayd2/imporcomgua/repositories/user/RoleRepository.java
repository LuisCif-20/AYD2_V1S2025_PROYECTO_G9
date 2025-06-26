package com.ayd2.imporcomgua.repositories.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.user.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    
}
