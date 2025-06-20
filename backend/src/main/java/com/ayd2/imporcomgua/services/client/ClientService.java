package com.ayd2.imporcomgua.services.client;

import java.util.List;

import com.ayd2.imporcomgua.dto.client.ClientResponseDTO;
import com.ayd2.imporcomgua.dto.client.ClientSearchRequestDTO;
import com.ayd2.imporcomgua.dto.client.NewClientRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateClientRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface ClientService {
    
    ClientResponseDTO getClient(Long id) throws NotFoundException;
    List<ClientResponseDTO> getAllClients(ClientSearchRequestDTO clientSearchRequestDTO);
    ClientResponseDTO createClient(NewClientRequestDTO newClientRequestDTO) throws NotFoundException;
    ClientResponseDTO updateClient(Long id, UpdateClientRequestDTO updateClientRequestDTO) throws NotFoundException;
    void deleteClient(Long id) throws NotFoundException;

}
