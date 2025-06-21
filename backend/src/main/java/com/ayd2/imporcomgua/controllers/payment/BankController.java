package com.ayd2.imporcomgua.controllers.payment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.payment.BankResponseDTO;
import com.ayd2.imporcomgua.services.payment.BankService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/banks")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping
    public ResponseEntity<List<BankResponseDTO>> getAllBanks() {
        List<BankResponseDTO> banks = bankService.getAllBanks();
        return ResponseEntity.ok(banks);
    }
}
