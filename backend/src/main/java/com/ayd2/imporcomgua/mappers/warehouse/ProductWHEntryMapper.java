package com.ayd2.imporcomgua.mappers.warehouse;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ayd2.imporcomgua.dto.warehouse.NewProductWHEntryRequestDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHEntryResponseDTO;
import com.ayd2.imporcomgua.models.warehouse.ProductWarehouseEntry;

@Mapper(componentModel = "spring")
public interface ProductWHEntryMapper {

    @Mapping(target = "productName", source = "product.name")
    ProductWHEntryResponseDTO toProductWHEntryResponseDTO(ProductWarehouseEntry productWarehouseEntry);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductWarehouseEntry toProductWarehouseEntry(NewProductWHEntryRequestDTO productWHEntryRequestDTO);
    
}
