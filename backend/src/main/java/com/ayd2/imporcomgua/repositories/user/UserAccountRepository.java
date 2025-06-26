package com.ayd2.imporcomgua.repositories.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.user.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    Optional<UserAccount> findByEmail(String email);
    boolean existsByEmail(String email);

}
