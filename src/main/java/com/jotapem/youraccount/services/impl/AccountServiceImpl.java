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

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public AccountDetailsDTO create(AccountCreateDTO account) {
        Account accountToCreate = accountMapper.toEntity(account);
        accountToCreate.setAgency("0864");
        accountToCreate.setNumber("31234123-1");
        Account accountCreated = accountRepository.save(accountToCreate);
        return accountMapper.toDetailsDTO(accountCreated);
    }

    @Override
    public void update(UUID id, AccountCreateDTO account) {
        Account accountFound = verifyAndGetIfExists(id);

        Account accountToUpdate = accountMapper.toEntity(account);
        accountToUpdate.setCreatedAt(accountFound.getCreatedAt());
        accountToUpdate.setId(id);
        accountRepository.save(accountToUpdate);
    }

    public Account verifyAndGetIfExists(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }

    @Override
    public Page<AccountDetailsDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).map(accountMapper::toDetailsDTO);
    }
}
