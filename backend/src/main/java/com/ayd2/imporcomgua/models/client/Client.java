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

    @Column(length = 10, unique = true)
    private String code;

    @Size(max = 100)
    @Column(nullable = false)
    private String contactName;

    @Size(max = 100)
    @Column
    private String businessName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Municipality municipality;

    @Size(max = 255)
    @Column
    private String address;

    @Size(min = 9, max = 9)
    @Column(length = 9)
    private String nit;

    @Size(max = 100)
    @Column
    private String warehouseManager;

    @Size(min = 9, max = 9)
    @Column(name = "phone", length = 9)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(length = 7, nullable = false)
    private SaleType saleType;

    @Column
    private String notes;

    @Column(nullable = false)
    private boolean isActive = true;

    @PostPersist
    public void generateCode() {
        if (this.municipality != null && this.municipality.getDepartment() != null) {
            this.code = this.municipality.getDepartment().getCode() + this.id;
        }
    }
    
}
