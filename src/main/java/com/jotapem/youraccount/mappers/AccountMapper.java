package com.jotapem.youraccount.mappers;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toModel(AccountCreateDTO account);
}
