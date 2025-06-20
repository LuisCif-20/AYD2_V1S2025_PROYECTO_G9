package com.ayd2.imporcomgua.services.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ayd2.imporcomgua.dto.generic.PagedResponseDTO;
import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;
import com.ayd2.imporcomgua.mappers.generic.GenericPageMapper;
import com.ayd2.imporcomgua.mappers.product.PresentationMapper;
import com.ayd2.imporcomgua.models.product.Presentation;
import com.ayd2.imporcomgua.repositories.product.PresentationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresentationServiceImpl implements PresentationService{
    private final PresentationRepository presentationRepository;
    private final PresentationMapper presentationMapper;
    private final GenericPageMapper genericPageMapper;

    @Override
    public PagedResponseDTO<PresentationResponseDTO> getAllPresentations(Pageable pageable) {
        Page<Presentation> page = presentationRepository.findAll(pageable);
        Page<PresentationResponseDTO> dtoPage = page.map(presentationMapper::toPresentationResponseDTO);
        return genericPageMapper.toPagedResponse(dtoPage);
    }
    
}
