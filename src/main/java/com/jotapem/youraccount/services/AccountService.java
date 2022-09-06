package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.entities.Account;

public interface AccountService {

    Account create(AccountCreateDTO account);
}
