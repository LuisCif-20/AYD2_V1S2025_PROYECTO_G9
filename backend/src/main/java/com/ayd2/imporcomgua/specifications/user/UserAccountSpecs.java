package com.ayd2.imporcomgua.specifications.user;

import org.springframework.data.jpa.domain.Specification;

import com.ayd2.imporcomgua.models.user.UserAccount;

public class UserAccountSpecs {

    public static Specification<UserAccount> hasRoleName(String roleName) {
        return (root, query, criteriaBuilder) -> roleName == null ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("role").get("name")),
                        "%" + roleName.toLowerCase() + "%");
    }

    public static Specification<UserAccount> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> isActive == null ? null
                : criteriaBuilder.equal(root.get("isActive"), isActive);
    }

}
