package com.ayd2.imporcomgua.specifications.client;

import org.springframework.data.jpa.domain.Specification;

import com.ayd2.imporcomgua.models.client.Client;

public class ClientSpecs {
    
    public static Specification<Client> hasCode(String code) {
        return (root, query, criteriaBuilder) -> code == null ? null
                : criteriaBuilder.like(root.get("code"), code);
    }

}
