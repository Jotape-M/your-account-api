package com.jotapem.youraccount.services.impl;

import com.jotapem.youraccount.mappers.AccountMapper;
import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.AccountDetailsDTO;
import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.repositories.AccountRepository;
import com.jotapem.youraccount.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountDetailsDTO create(AccountCreateDTO account) {
        verifyIfExists(account.getEmail());
        Account accountToCreate = accountMapper.toEntity(account);
        Account accountCreated = accountRepository.save(accountToCreate);
        return accountMapper.toDetailsDTO(accountCreated);
    }

    @Override
    public void verifyIfExists(String email) {
        accountRepository.findByEmail(email).ifPresent(account -> {
            throw new EntityExistsException("An account already exists with this email " + email);
        });
    }

    @Override
    public Page<AccountDetailsDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).map(accountMapper::toDetailsDTO);
    }
}
