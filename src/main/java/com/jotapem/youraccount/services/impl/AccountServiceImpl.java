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
import javax.persistence.EntityNotFoundException;
import java.util.UUID;


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
    public void update(UUID id, AccountCreateDTO account) {
        Account accountFound = verifyAndGetIfExists(id);

        if(!accountFound.getEmail().equals(account.getEmail())) {
            verifyIfExists(account.getEmail());
        }

        Account accountToUpdate = accountMapper.toEntity(account);
        accountToUpdate.setCreatedAt(accountFound.getCreatedAt());
        accountToUpdate.setId(id);
        accountRepository.save(accountToUpdate);
    }

    @Override
    public void verifyIfExists(String email) {
        accountRepository.findByEmail(email).ifPresent(account -> {
            throw new EntityExistsException("An account already exists with this email " + email);
        });
    }

    public Account verifyAndGetIfExists(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }

    @Override
    public Page<AccountDetailsDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).map(accountMapper::toDetailsDTO);
    }
}
