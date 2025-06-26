package com.ayd2.imporcomgua.services.client;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.dto.client.NewBusinessRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateBusinessRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.client.BusinessMapper;
import com.ayd2.imporcomgua.models.client.Business;
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

    @Override
    public BusinessResponseDTO createBusiness(NewBusinessRequestDTO newBusinessRequestDTO) {
        final Business business = businessMapper.toBusiness(newBusinessRequestDTO);
        final Business newBusiness = businessRepository.save(business);
        return businessMapper.toBusinessResponseDTO(newBusiness);
    }

    @Override
    public BusinessResponseDTO updateBusiness(UUID id, UpdateBusinessRequestDTO updateBusinessRequestDTO)
            throws NotFoundException {
        final Business business = businessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el negocio con id: " + id));
        businessMapper.UpdateBusinessFromDTO(updateBusinessRequestDTO, business);
        final Business updatedBusiness = businessRepository.save(business);
        return businessMapper.toBusinessResponseDTO(updatedBusiness);
    }

    @Override
    public void deleteBusiness(UUID id) throws NotFoundException {
        final Business business = businessRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el negocio con id: " + id));
        business.setIsActive(false);
        businessRepository.save(business);
    }
    
}
