package com.ayd2.imporcomgua.mappers.sale;

import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.models.sale.Sale;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SaleDetailMapper.class})
public interface SaleMapper {
    SaleResponseDTO toSaleResponseDTO(Sale sale);
}