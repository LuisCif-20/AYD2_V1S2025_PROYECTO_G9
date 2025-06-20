package com.ayd2.imporcomgua.models.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class Department {

    @Id
    @Column(length = 2)
    private String code;

    @Column(length = 50, nullable = false)
    private String name;
    
}
