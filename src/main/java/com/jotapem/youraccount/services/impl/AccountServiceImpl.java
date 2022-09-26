package com.jotapem.youraccount.services.impl;

import com.jotapem.youraccount.mappers.AccountMapper;
import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.account.AccountUpdateDTO;
import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.models.entities.Owner;
import com.jotapem.youraccount.repositories.AccountRepository;
import com.jotapem.youraccount.services.AccountService;
import com.jotapem.youraccount.services.OwnerService;
import com.jotapem.youraccount.validations.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final OwnerService ownerService;

    private final AccountValidator accountValidator;

    @Override
    public AccountDetailsDTO create(AccountCreateDTO account) {
        Account accountToCreate = accountMapper.toEntity(account);
        Owner ownerCreated = ownerService.create(account.getOwner());
        accountToCreate.setNumber("31234123-1");
        accountToCreate.setOwner(ownerCreated);
        Account accountCreated = accountRepository.save(accountToCreate);
        return accountMapper.toDetailsDTO(accountCreated);
    }

    @Override
    public void update(UUID id, AccountUpdateDTO account) {
        Account accountFound = accountValidator.verifyAndGetIfExists(id);

        Account accountToUpdate = accountMapper.toEntity(account);
        accountToUpdate.setOwner(accountFound.getOwner());
        accountToUpdate.setNumber(accountFound.getNumber());
        accountToUpdate.setCreatedAt(accountFound.getCreatedAt());
        accountToUpdate.setId(id);
        accountRepository.save(accountToUpdate);
    }

    @Override
    public Page<AccountDetailsDTO> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable).map(accountMapper::toDetailsDTO);
    }

    @Override
    public void deleteById(UUID id) {
        Account accountFound = accountValidator.verifyAndGetIfExists(id);
        accountRepository.delete(accountFound);
    }

    @Override
    public AccountDetailsDTO findById(UUID id) {
        Account accountFound = accountValidator.verifyAndGetIfExists(id);
        return accountMapper.toDetailsDTO(accountFound);
    }
}
