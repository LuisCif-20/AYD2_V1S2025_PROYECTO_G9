package com.ayd2.imporcomgua.mappers.sale;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ayd2.imporcomgua.dto.sales.NewSaleDetailRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleDetailResponseDTO;
import com.ayd2.imporcomgua.models.sale.SaleDetail;

@Mapper(componentModel = "spring")
public interface SaleDetailMapper {
    
    @Mapping(target = "productName", source = "product.name")
    SaleDetailResponseDTO toSaleDetailResponseDTO(SaleDetail saleDetail);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "subtotal", ignore = true)
    @Mapping(target = "sale", ignore = true)
    SaleDetail toSaleDetail(NewSaleDetailRequestDTO saleDetailRequestDTO);
}
