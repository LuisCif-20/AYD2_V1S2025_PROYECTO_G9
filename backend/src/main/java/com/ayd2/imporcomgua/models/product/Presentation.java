package com.ayd2.imporcomgua.models.product;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Presentation {

    @Id
    private Long id;

    @Column
    private String name;
}