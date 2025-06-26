package com.ayd2.imporcomgua.mappers.product;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.ayd2.imporcomgua.dto.products.NewProductRequestDTO;
import com.ayd2.imporcomgua.dto.products.ProductResponseDTO;
import com.ayd2.imporcomgua.dto.products.UpdateProductRequestDTO;
import com.ayd2.imporcomgua.models.product.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponseDTO toProductResponseDTO(Product product);

    @Mapping(target = "presentation", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    Product toProduct(NewProductRequestDTO productRequestDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "presentation", ignore = true)
    void updateProductFromDTO(UpdateProductRequestDTO dto, @MappingTarget Product product);
}
