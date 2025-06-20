package com.ayd2.imporcomgua.specifications.product;

import org.springframework.data.jpa.domain.Specification;
import com.ayd2.imporcomgua.models.product.Product;

public class ProductSpecs {

    public static Specification<Product> hasCode(String code) {
        return (root, query, criteriaBuilder) -> code == null ? null
                : criteriaBuilder.equal(root.get("code"), code);
    }

    public static Specification<Product> nameContains(String name) {
        return (root, query, criteriaBuilder) -> name == null ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%");
    }

    public static Specification<Product> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> {
            if (isActive == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("isActive"), isActive);
        };
    }
}