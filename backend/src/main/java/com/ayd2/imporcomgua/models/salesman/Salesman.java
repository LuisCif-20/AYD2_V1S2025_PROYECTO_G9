package com.ayd2.imporcomgua.models.salesman;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class Salesman {

    @Id
    private UUID code;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String address;

    @Column
    private Double commissionPercent;

    @Column
    private Boolean isActive;
}