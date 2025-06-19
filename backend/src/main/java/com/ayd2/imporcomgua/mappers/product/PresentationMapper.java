package com.ayd2.imporcomgua.mappers.product;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ayd2.imporcomgua.dto.products.PresentationResponseDTO;
import com.ayd2.imporcomgua.models.product.Presentation;

@Mapper(componentModel = "spring")
public interface PresentationMapper {
    PresentationMapper INSTANCE = Mappers.getMapper(PresentationMapper.class);

    PresentationResponseDTO toPresentationResponseDTO(Presentation presentation);
}
