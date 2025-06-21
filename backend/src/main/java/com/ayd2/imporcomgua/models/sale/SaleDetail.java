package com.ayd2.imporcomgua.models.sale;

import com.ayd2.imporcomgua.models.product.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sale_detail")
public class SaleDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product product;

    @Column
    private Integer quantity = 1;

    @Column(name = "unit_quantity")
    private Integer unitQuantity;

    @Column(name = "price_per_presentation")
    private Double pricePerPresentation;

    @Column
    private Double subtotal;
}