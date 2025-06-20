package com.ayd2.imporcomgua.mappers.sale;

import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.models.sale.Sale;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SaleMapper {
    SaleResponseDTO toSaleResponseDTO(Sale sale);
}