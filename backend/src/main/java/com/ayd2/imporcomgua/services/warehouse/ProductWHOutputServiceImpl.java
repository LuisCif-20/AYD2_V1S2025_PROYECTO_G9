package com.ayd2.imporcomgua.services.warehouse;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.dto.warehouse.ProductWHOutputRequestDTO;
import com.ayd2.imporcomgua.exceptions.DuplicatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.sale.SaleMapper;
import com.ayd2.imporcomgua.models.sale.PaymentStatus;
import com.ayd2.imporcomgua.models.sale.Sale;
import com.ayd2.imporcomgua.models.sale.SaleDetail;
import com.ayd2.imporcomgua.models.sale.SaleStatus;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.sale.SaleRepository;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ProductWHOutputServiceImpl implements ProductWHOutputService {
    private final SaleRepository saleRepository;
    private final InventoryRepository inventoryRepository;
    private final SaleMapper saleMapper;

    @Override
    public SaleResponseDTO processProductWHOutput(ProductWHOutputRequestDTO outputRequestDTO)
            throws NotFoundException, DuplicatedEntityException {

        Sale sale = saleRepository.findByIdAndSaleStatus(outputRequestDTO.saleId(), SaleStatus.VIGENTE)
                .orElseThrow(() -> new NotFoundException("Venta vigente no encontrada con ID: " + outputRequestDTO.saleId()));

        if (sale.getPaymentStatus() != PaymentStatus.PAGADO) {
            throw new IllegalStateException("No se puede procesar salida de inventario: la venta no está pagada");
        }

        if (sale.getWarehouseExitDate() != null) {
            throw new DuplicatedEntityException("Esta venta ya tuvo una salida de inventario registrada");
        }

        for (SaleDetail detail : sale.getDetails()) {
            Inventory inventory = inventoryRepository.findByProduct(detail.getProduct())
                    .orElseThrow(() -> new NotFoundException(
                            "Inventario no encontrado para producto: " + detail.getProduct().getCode()));

            // Validar que haya suficiente cantidad reservada
            if (inventory.getReservedQuantity() < detail.getQuantity()) {
                throw new IllegalStateException(
                        "Cantidad reservada insuficiente para el producto: " + detail.getProduct().getCode());
            }

            // Actualizar inventario
            inventory.setReservedQuantity(inventory.getReservedQuantity() - detail.getQuantity());
            inventory.setTotalQuantity(inventory.getTotalQuantity() - detail.getQuantity());
            inventory.setLastUpdated(LocalDate.now());

            inventoryRepository.save(inventory);
        }

        // 5. Actualizar venta con fecha de salida
        sale.setWarehouseExitDate(outputRequestDTO.exitDate());
        Sale updatedSale = saleRepository.save(sale);

        // 6. Retornar la venta actualizada
        return saleMapper.toSaleResponseDTO(updatedSale);
    }

}
