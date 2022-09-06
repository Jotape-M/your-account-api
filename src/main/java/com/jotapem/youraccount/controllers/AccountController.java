package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public ResponseEntity<Account> create(@RequestBody @Valid AccountCreateDTO account) {
         return ResponseEntity.ok(accountService.create(account));
    }
}
