package com.ayd2.imporcomgua.services.salesman;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.salesman.NewSalesmanRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanResponseDTO;
import com.ayd2.imporcomgua.dto.salesman.SalesmanSearchRequestDTO;
import com.ayd2.imporcomgua.dto.salesman.UpdateSalesmanRequestDTO;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.salesman.SalesmanMapper;
import com.ayd2.imporcomgua.models.salesman.Salesman;
import com.ayd2.imporcomgua.repositories.salesman.SalesmanRepository;
import com.ayd2.imporcomgua.specifications.salesman.SalesmanSpecs;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SalesmanServiceImpl implements SalesmanService {

    private final SalesmanRepository salesmanRepository;
    private final SalesmanMapper salesmanMapper;

    @Override
    public SalesmanResponseDTO getSalesman(UUID id) throws NotFoundException {
        final Salesman salesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el vendedor con el id: " + id));
        return salesmanMapper.toSalesmanResponseDTO(salesman);
    }

    @Override
    public List<SalesmanResponseDTO> getAllSalesmans(SalesmanSearchRequestDTO salesmanSearchRequestDTO) {
        final Specification<Salesman> spec = Specification
                .anyOf(SalesmanSpecs.hasCode(salesmanSearchRequestDTO.code()),
                        SalesmanSpecs.nameContains(salesmanSearchRequestDTO.name()))
                .and(SalesmanSpecs.isActive(salesmanSearchRequestDTO.active()));
        List<SalesmanResponseDTO> salesmans = salesmanRepository.findAll(spec)
                .stream()
                .map(salesmanMapper::toSalesmanResponseDTO)
                .toList();
        return salesmans;
    }

    @Override
    public SalesmanResponseDTO createSalesman(NewSalesmanRequestDTO newSalesmanRequestDTO) {
        final Salesman salesman = salesmanMapper.toSalesman(newSalesmanRequestDTO);
        final Salesman newSalesman = salesmanRepository.save(salesman);
        return salesmanMapper.toSalesmanResponseDTO(newSalesman);
    }

    @Override
    public SalesmanResponseDTO updateSalesmanResponseDTO(UUID id, UpdateSalesmanRequestDTO updateSalesmanRequestDTO)
            throws NotFoundException {
        final Salesman salesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el vendedor con el id: " + id));
        salesmanMapper.updateSalesmanFromDTO(updateSalesmanRequestDTO, salesman);
        final Salesman updatedSalesman = salesmanRepository.save(salesman);
        return salesmanMapper.toSalesmanResponseDTO(updatedSalesman);
    }

    @Override
    public void deleteSalesman(UUID id) throws NotFoundException {
        final Salesman salesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No existe el vendedor con el id: " + id));
        salesman.setIsActive(false);
        salesmanRepository.save(salesman);
    }

}
