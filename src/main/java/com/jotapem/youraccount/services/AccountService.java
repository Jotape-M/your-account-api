package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.AccountDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AccountService {
    AccountDetailsDTO create(AccountCreateDTO account);
    void update(UUID id, AccountCreateDTO accountCreateDTO);
    Page<AccountDetailsDTO> findAll(Pageable pageable);
}
