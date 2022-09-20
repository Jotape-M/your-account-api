package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.controllers.docs.AccountControllerDocs;
import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.PageResultDTO;
import com.jotapem.youraccount.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController implements AccountControllerDocs {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDetailsDTO create(@RequestBody @Valid AccountCreateDTO account) {
         return accountService.create(account);
    }

    @GetMapping
    public ResponseEntity<PageResultDTO<AccountDetailsDTO>> getAll(@ParameterObject Pageable pageable) {
        PageResultDTO<AccountDetailsDTO> pageResultDTO = new PageResultDTO(accountService.findAll(pageable));
        return ResponseEntity.ok(pageResultDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid AccountCreateDTO account) {
        accountService.update(id, account);
    }
}
