package com.ayd2.imporcomgua.models.warehouse;

import com.ayd2.imporcomgua.models.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "product")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    private Product product;

    @Column
    private LocalDate lastUpdated;

    @Column
    private Integer totalQuantity;

    @Column
    private Integer availableQuantity;

    @Column
    private Integer reservedQuantity;

    @Column
    private Integer lowStockThreshold = 5;
    
    @Column
    private Boolean isLowStockAlertSent = false;

    @PrePersist
    @PreUpdate
    private void preSave() {
        if (lastUpdated == null) {
            lastUpdated = LocalDate.now();
        }
    }
}
