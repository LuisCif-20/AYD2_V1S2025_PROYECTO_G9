package com.ayd2.imporcomgua.services.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.mappers.client.BusinessMapper;
import com.ayd2.imporcomgua.repositories.client.BusinessRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BusinessServiceImpl implements BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    @Override
    public List<BusinessResponseDTO> getAllBusiness() {
        final List<BusinessResponseDTO> business = businessRepository.findAll()
                .stream()
                .map(businessMapper::toBusinessResponseDTO)
                .toList();
        return business;
    }
    
}
