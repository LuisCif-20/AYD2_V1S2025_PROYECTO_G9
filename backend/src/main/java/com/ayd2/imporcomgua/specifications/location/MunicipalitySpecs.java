package com.ayd2.imporcomgua.specifications.location;

import org.springframework.data.jpa.domain.Specification;

import com.ayd2.imporcomgua.models.location.Municipality;

public class MunicipalitySpecs {

    public static Specification<Municipality> hasDepartmentCode(String code) {
        return (root, query, criteriaBuilder) -> code == null ? null
                : criteriaBuilder.like(root.get("department").get("code"), code);
    }
    
}
