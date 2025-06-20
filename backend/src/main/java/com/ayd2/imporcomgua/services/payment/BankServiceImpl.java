package com.ayd2.imporcomgua.services.payment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.payment.BankResponseDTO;
import com.ayd2.imporcomgua.mappers.payment.BankMapper;
import com.ayd2.imporcomgua.repositories.payment.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BankServiceImpl implements BankService {

    private final BankMapper bankMapper;
    private final BankRepository bankRepository;

    @Override
    public List<BankResponseDTO> getAllBanks() {
        return bankRepository.findAll().stream()
                .map(bankMapper::toBankResponseDTO)
                .collect(Collectors.toList());
    }
    
}
