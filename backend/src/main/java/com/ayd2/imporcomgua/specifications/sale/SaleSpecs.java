package com.ayd2.imporcomgua.specifications.sale;

import com.ayd2.imporcomgua.models.sale.Sale;
import com.ayd2.imporcomgua.models.sale.SaleStatus;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

public class SaleSpecs {

    public static Specification<Sale> hasShipmentNumber(UUID shipmentNumber) {
        return (root, query, criteriaBuilder) -> shipmentNumber == null ? null
                : criteriaBuilder.equal(root.get("shipmentNumber"), shipmentNumber);
    }

    public static Specification<Sale> clientContactNameContains(String contactName) {
        return (root, query, criteriaBuilder) -> contactName == null ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("client").get("contactName")),
                        "%" + contactName.toLowerCase() + "%");
    }

    public static Specification<Sale> clientBusinessNameContains(String businessName) {
        return (root, query, criteriaBuilder) -> businessName == null ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("client").get("businessName")),
                        "%" + businessName.toLowerCase() + "%");
    }

    public static Specification<Sale> hasSaleStatus(SaleStatus saleStatus) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("saleStatus"), saleStatus == null ? SaleStatus.VIGENTE : saleStatus);
    }
}