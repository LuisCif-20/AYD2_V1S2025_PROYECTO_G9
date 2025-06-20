package com.ayd2.imporcomgua.models.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "code")
public class Municipality {

    @Id
    private String code;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_code")
    private Department department;
    
}
