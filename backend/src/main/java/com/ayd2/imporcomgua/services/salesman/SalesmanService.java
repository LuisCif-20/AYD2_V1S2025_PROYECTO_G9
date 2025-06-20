package com.ayd2.imporcomgua.services.salesman;

import java.util.List;
import java.util.UUID;

import com.ayd2.imporcomgua.dto.salesman.NewSalesmanRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanResponseDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanSearchRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.UpdateSalesmanRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;

public interface SalesmanService {
    
    SalesmanResponseDTO getSalesman(UUID id) throws NotFoundException;
    List<SalesmanResponseDTO> getAllSalesmans(SalesmanSearchRequestDTO salesmanSearchRequestDTO);
    SalesmanResponseDTO createSalesman(NewSalesmanRequestDTO newSalesmanRequestDTO);
    SalesmanResponseDTO updateSalesmanResponseDTO(UUID id, UpdateSalesmanRequestDTO updateSalesmanRequestDTO) throws NotFoundException;
    void deleteSalesman(UUID id) throws NotFoundException;

}
