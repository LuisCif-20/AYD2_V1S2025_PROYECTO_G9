package com.ayd2.imporcomgua.models.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class Product {

    @Id
    private String code;

    @Column
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @Column
    private Integer unitsPerPresentation;

    @Column
    private Double pricePerPresentation;

    @Column
    private Boolean isActive = true;
}