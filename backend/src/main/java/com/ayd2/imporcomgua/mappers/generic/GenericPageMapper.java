package com.ayd2.imporcomgua.mappers.generic;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.ayd2.imporcomgua.dto.generic.PagedResponseDTO;

@Mapper(componentModel = "spring")
public interface GenericPageMapper {

    default <T> PagedResponseDTO<T> toPagedResponse(Page<T> page) {
        return new PagedResponseDTO<>(
            page.getContent(),
            page.getTotalElements(),
            page.getNumber(),
            page.getTotalPages(),
            page.isFirst(),
            page.isLast(),
            page.hasNext(),
            page.hasPrevious()
        );
    }
}

