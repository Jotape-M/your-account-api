package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.PageResultDTO;
import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.account.AccountFilterDTO;
import com.jotapem.youraccount.models.dto.account.AccountUpdateDTO;

import java.util.UUID;

public interface AccountService {
    AccountDetailsDTO create(AccountCreateDTO account);
    void update(UUID id, AccountUpdateDTO accountCreateDTO);
    PageResultDTO<AccountDetailsDTO> getPaged(AccountFilterDTO filterDTO);
    void deleteById(UUID id);
    AccountDetailsDTO findById(UUID id);
}
