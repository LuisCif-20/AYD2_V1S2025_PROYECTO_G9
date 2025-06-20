package com.ayd2.imporcomgua.mappers.payment;

import org.mapstruct.Mapper;

import com.ayd2.imporcomgua.dto.payment.BankResponseDTO;
import com.ayd2.imporcomgua.models.payment.Bank;

@Mapper(componentModel = "spring")
public interface BankMapper {
    
    BankResponseDTO toBankResponseDTO(Bank bank);
}
