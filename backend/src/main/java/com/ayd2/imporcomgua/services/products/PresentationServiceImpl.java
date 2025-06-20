package com.ayd2.imporcomgua.services.products;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;
import com.ayd2.imporcomgua.mappers.product.PresentationMapper;
import com.ayd2.imporcomgua.models.product.Presentation;
import com.ayd2.imporcomgua.repositories.product.PresentationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    private final PresentationMapper presentationMapper;

    @Override
    public List<PresentationResponseDTO> getAllPresentations() {
        List<Presentation> presentations = presentationRepository.findAll();
        return presentations.stream()
                .map(presentationMapper::toPresentationResponseDTO)
                .collect(Collectors.toList());
    }

}
