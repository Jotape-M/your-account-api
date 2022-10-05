package com.jotapem.youraccount.mappers;

import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.account.AccountFilterDTO;
import com.jotapem.youraccount.models.dto.account.AccountUpdateDTO;
import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.repositories.specifications.AccountSpecification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.jpa.domain.Specification;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Mapping(target = "owner", ignore = true)
    public abstract Account toEntity(AccountCreateDTO accountCreateDTO);

    public abstract AccountDetailsDTO toDetailsDTO(Account account);

    public abstract Account toEntity(AccountUpdateDTO accountUpdateDTO);

    public Specification<Account> toSpecification(AccountFilterDTO filterDto) {
        return Specification.where(AccountSpecification.withAgencyOptional(filterDto.getAgency()));
    }
}
