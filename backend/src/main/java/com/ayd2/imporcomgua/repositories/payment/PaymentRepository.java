package com.ayd2.imporcomgua.repositories.payment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ayd2.imporcomgua.models.payment.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{
    
}
