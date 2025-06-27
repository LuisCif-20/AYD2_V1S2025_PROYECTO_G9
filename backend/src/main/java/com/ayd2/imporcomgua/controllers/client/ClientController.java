package com.ayd2.imporcomgua.controllers.client;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayd2.imporcomgua.dto.client.ClientResponseDTO;
import com.ayd2.imporcomgua.dto.client.ClientSearchRequestDTO;
import com.ayd2.imporcomgua.dto.client.NewClientRequestDTO;
import com.ayd2.imporcomgua.dto.client.UpdateClientRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.services.client.ClientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1.0/clients")
@RequiredArgsConstructor
// @PreAuthorize("hasAuthority('GERENTE_GENERAL')")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable Long id) throws NotFoundException {
        final ClientResponseDTO clientResponseDTO = clientService.getClient(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClient(@Valid ClientSearchRequestDTO clientSearchRequestDTO)
            throws NotFoundException {
        final List<ClientResponseDTO> clients = clientService.getAllClients(clientSearchRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @PostMapping
    // @PreAuthorize("hasAnyAuthority('GERENTE_GENERAL', 'GERENTE_VENTAS_FINANZAS')")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody @Valid NewClientRequestDTO newClientRequestDTO)
            throws NotFoundException {
        final ClientResponseDTO clientResponseDTO = clientService.createClient(newClientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable Long id,
            @RequestBody @Valid UpdateClientRequestDTO updateClientRequestDTO) throws NotFoundException {
        final ClientResponseDTO clientResponseDTO = clientService.updateClient(id, updateClientRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) throws NotFoundException {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
