package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.AccountDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    AccountDetailsDTO create(AccountCreateDTO account);
    void verifyIfExists(String email);
    Page<AccountDetailsDTO> findAll(Pageable pageable);
}
