package com.ayd2.imporcomgua.services.sales;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayd2.imporcomgua.dto.sales.NewSaleDetailRequestDTO;
import com.ayd2.imporcomgua.dto.sales.NewSaleRequestDTO;
import com.ayd2.imporcomgua.dto.sales.SaleResponseDTO;
import com.ayd2.imporcomgua.dto.sales.SaleSearchRequestDTO;
import com.ayd2.imporcomgua.exceptions.InvalidPaymentTypeException;
import com.ayd2.imporcomgua.exceptions.NoStockException;
import com.ayd2.imporcomgua.exceptions.NotActivatedEntityException;
import com.ayd2.imporcomgua.exceptions.NotFoundException;
import com.ayd2.imporcomgua.mappers.sale.SaleDetailMapper;
import com.ayd2.imporcomgua.mappers.sale.SaleMapper;
import com.ayd2.imporcomgua.models.client.Client;
import com.ayd2.imporcomgua.models.client.SaleType;
import com.ayd2.imporcomgua.models.product.Product;
import com.ayd2.imporcomgua.models.sale.PaymentStatus;
import com.ayd2.imporcomgua.models.sale.PaymentType;
import com.ayd2.imporcomgua.models.sale.Sale;
import com.ayd2.imporcomgua.models.sale.SaleDetail;
import com.ayd2.imporcomgua.models.sale.SaleStatus;
import com.ayd2.imporcomgua.models.salesman.Salesman;
import com.ayd2.imporcomgua.models.warehouse.Inventory;
import com.ayd2.imporcomgua.repositories.client.ClientRepository;
import com.ayd2.imporcomgua.repositories.product.ProductRepository;
import com.ayd2.imporcomgua.repositories.sale.SaleRepository;
import com.ayd2.imporcomgua.repositories.salesman.SalesmanRepository;
import com.ayd2.imporcomgua.repositories.warehouse.InventoryRepository;
import com.ayd2.imporcomgua.services.mail.MailService;
import com.ayd2.imporcomgua.specifications.sale.SaleSpecs;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private final SalesmanRepository salesmanRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final SaleMapper saleMapper;
    private final SaleDetailMapper saleDetailMapper;
    private final MailService mailService;

    @Override
    public SaleResponseDTO getSale(UUID id) throws NotFoundException {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con ID: " + id));
        return saleMapper.toSaleResponseDTO(sale);
    }

    @Override
    public List<SaleResponseDTO> getAllSales(SaleSearchRequestDTO saleSearchRequestDTO) {
        Specification<Sale> spec = Specification.anyOf(
                SaleSpecs.hasShipmentNumber(saleSearchRequestDTO.shipmentNumber()),
                SaleSpecs.clientContactNameContains(saleSearchRequestDTO.clientName()),
                SaleSpecs.clientBusinessNameContains(saleSearchRequestDTO.clientName())).and(
                        SaleSpecs.hasSaleStatus(saleSearchRequestDTO.status()))
                .and(
                        SaleSpecs.hasPaymentStatusIn(saleSearchRequestDTO.paymentStatus()));

        List<Sale> sales = saleRepository.findAll(spec);
        return sales.stream()
                .map(saleMapper::toSaleResponseDTO)
                .toList();
    }

    @Override
    public void deleteSale(UUID id) throws NotFoundException {
        // 1. Obtener la venta con sus detalles
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con ID: " + id));

        if (sale.getPaymentStatus() == PaymentStatus.PAGADO) {
            throw new IllegalStateException("La venta ya está pagada y no puede ser anulada");
        }
        // 2. Validar que no esté ya anulada
        if (sale.getSaleStatus() == SaleStatus.ANULADA) {
            throw new IllegalStateException("La venta ya está anulada");
        }

        // 3. Revertir el inventario para cada detalle
        for (SaleDetail detail : sale.getDetails()) {
            Inventory inventory = inventoryRepository.findByProduct(detail.getProduct())
                    .orElseThrow(() -> new NotFoundException(
                            "Inventario no encontrado para producto: " + detail.getProduct().getCode()));

            // Revertir: quitar de reservado y sumar a disponible
            inventory.setReservedQuantity(inventory.getReservedQuantity() - detail.getQuantity());
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() + detail.getQuantity());

            if (inventory.getAvailableQuantity() > inventory.getLowStockThreshold()) {
                inventory.setIsLowStockAlertSent(false);
            }

            inventoryRepository.save(inventory);
        }

        // 4. Marcar la venta como anulada
        sale.setSaleStatus(SaleStatus.ANULADA);
        saleRepository.save(sale);
    }

    @Override
    public SaleResponseDTO createSale(NewSaleRequestDTO saleRequestDTO)
            throws NotFoundException, InvalidPaymentTypeException, NoStockException, NotActivatedEntityException {

        // 1. Validar y obtener entidades relacionadas
        Client client = clientRepository.findById(saleRequestDTO.clientId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        Salesman salesman = salesmanRepository.findById(saleRequestDTO.salesmanCode())
                .orElseThrow(() -> new NotFoundException("Vendedor no encontrado"));

        if (client.getIsActive() == null || !client.getIsActive()) {
            throw new NotActivatedEntityException(
                    String.format("El cliente %s (Código: %s) se encuentra suspendido.",
                            client.getContactName(),
                            client.getCode()));
        }

        if (salesman.getIsActive() == null || !salesman.getIsActive()) {
            throw new NotActivatedEntityException(
                    String.format("El vendedor %s %s se encuentra suspendido.",
                            salesman.getFirstName(),
                            salesman.getLastName()));
        }

        // 2. Validar tipo de pago
        validatePaymentType(client, saleRequestDTO);

        // 3. Mapear DTO a entidad Sale (ignorando relaciones y campos calculados)
        Sale sale = saleMapper.toSale(saleRequestDTO);

        // 4. Establecer relaciones y valores adicionales
        sale.setClient(client);
        sale.setSalesman(salesman);
        sale.setPaymentStatus(PaymentStatus.PENDIENTE);
        sale.setSaleStatus(SaleStatus.VIGENTE);
        sale.setShipmentNumber(UUID.randomUUID());
        // Si es CONTADO, forzar días de crédito a 0
        if (saleRequestDTO.paymentType() == PaymentType.CONTADO) {
            sale.setCreditDays(0);
        }

        // 5. Procesar detalles con validación de inventario
        List<SaleDetail> details = processSaleDetails(saleRequestDTO.details(), sale);
        sale.setDetails(details);

        // 6. Calcular y establecer total
        double total = calculateAndSetTotal(sale);
        sale.setTotal(total);
        sale.setRemainingBalance(total);

        // 7. Guardar y retornar respuesta
        Sale savedSale = saleRepository.save(sale);
        return saleMapper.toSaleResponseDTO(savedSale);
    }

    private void validatePaymentType(Client client, NewSaleRequestDTO request)
            throws InvalidPaymentTypeException {

        if (client.getSaleType() == SaleType.AMBAS) {
            return;
        }

        if (!client.getSaleType().name().equals(request.paymentType().name())) {
            throw new InvalidPaymentTypeException(
                    client.getId().toString(),
                    request.paymentType());
        }
    }

    private List<SaleDetail> processSaleDetails(List<NewSaleDetailRequestDTO> detailDTOs, Sale sale)
            throws NoStockException, NotFoundException, NotActivatedEntityException {

        List<SaleDetail> details = new ArrayList<>();

        for (NewSaleDetailRequestDTO dto : detailDTOs) {
            Product product = productRepository.findById(dto.productCode())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + dto.productCode()));

            Inventory inventory = inventoryRepository.findByProduct(product)
                    .orElseThrow(() -> new NotFoundException(
                            "Inventario no encontrado para producto: " + dto.productCode()));


            // Validar que el producto esté activo
            if (product.getIsActive() == null || !product.getIsActive()) {
                throw new NotActivatedEntityException(
                        String.format("El producto %s (Código: %s) se encuentra descontinuado.",
                                product.getName(),
                                product.getCode()));
            }
            
            int requestedQuantity = dto.quantity();

            if (inventory.getAvailableQuantity() < requestedQuantity) {
                throw new NoStockException(product.getCode());
            }

            inventory.setAvailableQuantity(inventory.getAvailableQuantity() - requestedQuantity);
            inventory.setReservedQuantity(inventory.getReservedQuantity() + requestedQuantity);
            inventory.setLastUpdated(LocalDate.now());

            inventoryRepository.save(inventory);
            // Enviar notificación de inventario si es necesario
            mailService.sendInventoryNotification(inventory);

            SaleDetail detail = saleDetailMapper.toSaleDetail(dto);
            detail.setProduct(product);
            detail.setSale(sale);
            detail.setSubtotal(dto.pricePerPresentation() * requestedQuantity);

            details.add(detail);
        }

        return details;
    }

    private double calculateAndSetTotal(Sale sale) {
        double total = sale.getDetails().stream()
                .mapToDouble(SaleDetail::getSubtotal)
                .sum();
        return total;
    }

}
