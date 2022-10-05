package com.jotapem.youraccount.repositories.specifications;

import com.jotapem.youraccount.models.entities.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {

    public static Specification<Account> withAgencyOptional(String agency) {
        return (root, criteriaQuery, criteriaBuilder) -> agency == null ? null : criteriaBuilder.equal(root.get("agency"), agency);
    }
}
