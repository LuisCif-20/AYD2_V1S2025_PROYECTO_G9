package com.ayd2.imporcomgua.services.client;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.client.ClientResponseDTO;
import com.ayd2.imporcomgua.dto.client.ClientSearchRequestDTO;
import com.ayd2.imporcomgua.dto.client.NewClientRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateClientRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.client.ClientMapper;
import com.ayd2.imporcomgua.models.client.Client;
import com.ayd2.imporcomgua.models.location.Municipality;
import com.ayd2.imporcomgua.repositories.client.ClientRepository;
import com.ayd2.imporcomgua.repositories.location.MunicipalityRepository;
import com.ayd2.imporcomgua.specifications.client.ClientSpecs;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final MunicipalityRepository municipalityRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponseDTO getClient(Long id) throws NotFoundException {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el cliente con el id: " + id.toString()));
        return clientMapper.toClientResponseDTO(client);
    }

    @Override
    public List<ClientResponseDTO> getAllClients(ClientSearchRequestDTO clientSearchRequestDTO) {
        Specification<Client> spec = Specification
                .anyOf(ClientSpecs.hasCode(null), ClientSpecs.nameContains(clientSearchRequestDTO.name()))
                .and(ClientSpecs.isActive(clientSearchRequestDTO.active()));
        final List<ClientResponseDTO> clients = clientRepository.findAll(spec)
                .stream()
                .map(clientMapper::toClientResponseDTO)
                .toList();
        return clients;
    }

    @Override
    public ClientResponseDTO createClient(NewClientRequestDTO newClientRequestDTO) throws NotFoundException {
        final String municipalityCode = newClientRequestDTO.municipalityCode();
        final Municipality municipality = municipalityRepository.findById(municipalityCode)
                .orElseThrow(() -> new NotFoundException("No existe el municipio con el codigo: " + municipalityCode));
        final Client client = clientMapper.toClient(newClientRequestDTO);
        client.setMunicipality(municipality);
        final Client newClient = clientRepository.save(client);
        return clientMapper.toClientResponseDTO(newClient);
    }

    @Override
    public ClientResponseDTO updateClient(Long id, UpdateClientRequestDTO updateClientRequestDTO)
            throws NotFoundException {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el cliente con el id: " + id.toString()));
        clientMapper.updateClientFromDTO(updateClientRequestDTO, client);
        final Client updatedClient = clientRepository.save(client);
        return clientMapper.toClientResponseDTO(updatedClient);
    }

    @Override
    public void deleteClient(Long id) throws NotFoundException {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el cliente con el id: " + id.toString()));
        client.setIsActive(false);
        clientRepository.save(client);
    }

}
