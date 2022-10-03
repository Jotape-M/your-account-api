package com.jotapem.youraccount.repositories.specifications;

import com.jotapem.youraccount.models.entities.Owner;
import org.springframework.data.jpa.domain.Specification;

public class OwnerSpecification {

    public static Specification<Owner> withEmailOptional(String email){
        return (root, criteriaQuery, criteriaBuilder) -> email == null ? null :  criteriaBuilder.like(root.get("email"), email);
    }


    public static Specification<Owner> withNicknameOptional(String nicknameOptional){
        return (root, criteriaQuery, criteriaBuilder) -> nicknameOptional == null ? null :  criteriaBuilder.equal(root.get("nickname"), nicknameOptional);
    }

    public static Specification<Owner> withLastNameOptional(String lastNameOptional){
        return (root, criteriaQuery, criteriaBuilder) -> lastNameOptional == null ? null :  criteriaBuilder.equal(root.get("lastName"), lastNameOptional);
    }
}
