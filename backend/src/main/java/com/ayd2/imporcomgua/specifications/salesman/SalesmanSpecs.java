package com.ayd2.imporcomgua.specifications.salesman;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.ayd2.imporcomgua.models.salesman.Salesman;

public class SalesmanSpecs {

    public static Specification<Salesman> hasCode(UUID code) {
        return (root, query, criteriaBuilder) -> code == null ? null
                : criteriaBuilder.equal(root.get("code"), code);
    }

    public static Specification<Salesman> nameContains(String name) {
        return (root, query, criteriaBuilder) -> name == null ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + name.toLowerCase() + "%");
    }

    public static Specification<Salesman> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("isActive"), isActive == null ? true : isActive);
    }

}
