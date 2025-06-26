package com.ayd2.imporcomgua.models.client;

import com.ayd2.imporcomgua.models.location.Municipality;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
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

    @Column
    private String code;

    @Column
    private String contactName;

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @ManyToOne
    @JoinColumn(name = "municipality_code")
    private Municipality municipality;

    @Column
    private String address;

    @Column
    private String nit;

    @Column
    private String warehouseManager;

    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column
    private SaleType saleType;

    @Column
    private String notes;

    @Column
    private Boolean isActive = true;

    @PostPersist
    public void generateCode() {
        if (this.municipality != null && this.municipality.getDepartment() != null) {
            this.code = this.municipality.getDepartment().getCode() + this.id;
        }
    }
    
}
