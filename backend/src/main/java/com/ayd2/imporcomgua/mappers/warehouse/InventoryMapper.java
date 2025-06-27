package com.ayd2.imporcomgua.mappers.warehouse;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.ayd2.imporcomgua.dto.warehouse.UpdateInventoryRequestDTO;
import com.ayd2.imporcomgua.models.warehouse.Inventory;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "totalQuantity", ignore = true)
    @Mapping(target = "availableQuantity", ignore = true)
    @Mapping(target = "reservedQuantity", ignore = true)
    @Mapping(target = "isLowStockAlertSent", ignore = true)
    void updateInventoryFromDTO(UpdateInventoryRequestDTO dto, @MappingTarget Inventory inventory);
}
