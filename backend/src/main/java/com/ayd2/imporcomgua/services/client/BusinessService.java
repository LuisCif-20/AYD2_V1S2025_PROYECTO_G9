package com.ayd2.imporcomgua.services.client;

import java.util.List;
import java.util.UUID;

import com.ayd2.imporcomgua.dto.client.BusinessResponseDTO;
import com.ayd2.imporcomgua.dto.client.NewBusinessRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateBusinessRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface BusinessService {
    
    List<BusinessResponseDTO> getAllBusiness();
    BusinessResponseDTO createBusiness(NewBusinessRequestDTO newBusinessRequestDTO)
            throws DuplicatedEntityException;
    BusinessResponseDTO updateBusiness(UUID id, UpdateBusinessRequestDTO updateBusinessRequestDTO)
            throws NotFoundException, DuplicatedEntityException;
    void deleteBusiness(UUID id) throws NotFoundException;

}
