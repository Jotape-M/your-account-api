package com.jotapem.youraccount.validations;

import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountValidator {

    public final AccountRepository accountRepository;

    public Account verifyAndGetIfExists(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + id));
    }
}
