package com.ayd2.imporcomgua.dto.products;

public record ProductResponseDTO(
    String code,
    String name,
    PresentationResponseDTO presentation,
    Integer unitsPerPresentation,
    Double pricePerPresentation,
    Boolean isActive
) {}