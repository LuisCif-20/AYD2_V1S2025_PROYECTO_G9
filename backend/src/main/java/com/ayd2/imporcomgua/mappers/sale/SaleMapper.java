package com.ayd2.imporcomgua.mappers.sale;

import com.ayd2.imporcomgua.dto.sales.NewSaleRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.models.sale.Sale;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SaleDetailMapper.class})
public interface SaleMapper {
    SaleResponseDTO toSaleResponseDTO(Sale sale);

    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "paymentDate", ignore = true)
    @Mapping(target = "paymentStatus", ignore = true)
    @Mapping(target = "saleStatus", ignore = true)
    @Mapping(target = "salesman", ignore = true)
    @Mapping(target = "shipmentNumber", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "warehouseExitDate", ignore = true)
    @Mapping(target = "remainingBalance", ignore = true)
    Sale toSale(NewSaleRequestDTO saleRequestDTO);
}