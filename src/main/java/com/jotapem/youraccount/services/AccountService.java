package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.account.AccountUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountService {
    AccountDetailsDTO create(AccountCreateDTO account);
    void update(UUID id, AccountUpdateDTO accountCreateDTO);
    Page<AccountDetailsDTO> findAll(Pageable pageable);
    void deleteById(UUID id);
}
