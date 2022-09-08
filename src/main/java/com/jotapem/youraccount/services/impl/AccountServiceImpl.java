package com.jotapem.youraccount.services.impl;

import com.jotapem.youraccount.mappers.AccountMapper;
import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.repositories.AccountRepository;
import com.jotapem.youraccount.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public Account create(AccountCreateDTO account) {
        Account accountToCreate = accountMapper.toModel(account);
        return accountRepository.save(accountToCreate);
    }
}
