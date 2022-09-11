package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.AccountDetailsDTO;

public interface AccountService {

    AccountDetailsDTO create(AccountCreateDTO account);

    void verifyIfExists(String email);
}
