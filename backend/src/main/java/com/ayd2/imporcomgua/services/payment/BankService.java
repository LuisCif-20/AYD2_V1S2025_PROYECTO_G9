package com.ayd2.imporcomgua.services.payment;

import java.util.List;

import com.ayd2.imporcomgua.dto.payment.BankResponseDTO;

public interface BankService {
    List<BankResponseDTO> getAllBanks();
}
