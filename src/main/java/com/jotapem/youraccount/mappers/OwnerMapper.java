package com.jotapem.youraccount.mappers;

import com.jotapem.youraccount.models.dto.owner.OwnerCreateDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerDetailsDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerFilterDto;
import com.jotapem.youraccount.models.entities.Owner;
import com.jotapem.youraccount.repositories.specifications.OwnerSpecification;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.domain.Specification;

@Mapper(componentModel = "spring")
public abstract class OwnerMapper {

    public abstract Owner toOwnerEntity(OwnerCreateDTO ownerCreateDTO);

    public abstract OwnerDetailsDTO toDetailsDTO(Owner account);

    public Specification<Owner> toSpecification(OwnerFilterDto filterDto){
        switch (filterDto.getLogicOperator()){
            case AND:
                return toSpecificationWithAnd(filterDto);
            case OR:
                return toSpecificationWithOR(filterDto);
        }
        return toSpecificationWithAnd(filterDto);
    }

    private Specification<Owner> toSpecificationWithOR(OwnerFilterDto filterDto) {
        return Specification.where(OwnerSpecification.withEmailOptional(filterDto.getEmail()))
                .or(OwnerSpecification.withNicknameOptional(filterDto.getNickname()))
                .or(OwnerSpecification.withLastNameOptional(filterDto.getLastName()));
    }

    private Specification<Owner> toSpecificationWithAnd(OwnerFilterDto filterDto) {
        return Specification.where(OwnerSpecification.withEmailOptional(filterDto.getEmail()))
                .and(OwnerSpecification.withNicknameOptional(filterDto.getNickname()))
                .and(OwnerSpecification.withLastNameOptional(filterDto.getLastName()));
    }
}
