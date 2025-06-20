package com.ayd2.imporcomgua.models.warehouse;

import com.ayd2.imporcomgua.models.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductWarehouseEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private LocalDate entryDate;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @Column
    private Integer quantityPresentation;

    @Column
    private Integer unitsPerPresentation;

    @Column
    private String containerNumber;

    @Column
    private String ducaNumber;

    @Column
    private LocalDate ducaDate;

    @Column
    private String rectifiedDucaNumber;

    @Column
    private LocalDate rectifiedDucaDate;

    @Column
    private String notes;
}
