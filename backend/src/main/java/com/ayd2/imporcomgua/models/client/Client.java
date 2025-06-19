package com.ayd2.imporcomgua.models.client;

import com.ayd2.imporcomgua.models.location.Municipality;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 10, nullable = false, unique = true)
    private String code;

    @Size(max = 100)
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @Size(max = 100)
    @Column(name = "business_name")
    private String businessName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipality_code", nullable = false)
    private Municipality municipality;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(min = 9, max = 9)
    @Column(name = "nit", length = 9)
    private String nit;

    @Size(max = 100)
    @Column(name = "warehouse_manager")
    private String warehouseManager;

    @Size(min = 9, max = 9)
    @Column(name = "phone", length = 9)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "sale_type", length = 7, nullable = false)
    private SaleType saleType;

    @Lob
    @Column(name = "notes")
    private String notes;
    
}
