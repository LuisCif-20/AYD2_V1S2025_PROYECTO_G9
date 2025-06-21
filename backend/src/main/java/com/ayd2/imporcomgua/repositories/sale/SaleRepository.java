package com.ayd2.imporcomgua.repositories.sale;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ayd2.imporcomgua.models.sale.Sale;
import com.ayd2.imporcomgua.models.sale.SaleStatus;


public interface SaleRepository extends JpaRepository<Sale, UUID>, JpaSpecificationExecutor<Sale> {
    Optional<Sale> findByIdAndSaleStatus(UUID id, SaleStatus saleStatus);
}
