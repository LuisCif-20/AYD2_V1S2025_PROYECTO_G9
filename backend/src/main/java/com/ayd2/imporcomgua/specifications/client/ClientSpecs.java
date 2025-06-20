package com.ayd2.imporcomgua.specifications.client;

import org.springframework.data.jpa.domain.Specification;

import com.ayd2.imporcomgua.models.client.Client;

public class ClientSpecs {
    
    public static Specification<Client> hasCode(String code) {
        return (root, query, criteriaBuilder) -> code == null ? null
                : criteriaBuilder.like(root.get("code"), code);
    }

    public static Specification<Client> nameContains(String name) {
        return (root, query, criteriaBuilder) -> name == null ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("contactName")), 
                        "%" + name.toLowerCase() + "%");
    }

    public static Specification<Client> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("isActive"), isActive == null ? true : isActive);
    }

}
