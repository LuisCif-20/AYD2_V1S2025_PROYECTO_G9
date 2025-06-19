package com.ayd2.imporcomgua.services.products;

import org.springframework.data.domain.Pageable;

import com.ayd2.imporcomgua.dto.generic.PagedResponseDTO;
import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;

public interface PresentationService {
    PagedResponseDTO<PresentationResponseDTO> getAllPresentations(Pageable pageable);
}
