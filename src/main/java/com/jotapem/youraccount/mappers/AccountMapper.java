package com.jotapem.youraccount.mappers;

import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.account.AccountUpdateDTO;
import com.jotapem.youraccount.models.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "owner", ignore = true)
    Account toEntity(AccountCreateDTO accountCreateDTO);

    AccountDetailsDTO toDetailsDTO(Account account);

    Account toEntity(AccountUpdateDTO accountUpdateDTO);
}
