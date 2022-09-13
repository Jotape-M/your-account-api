package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.models.dto.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.AccountDetailsDTO;
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
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDetailsDTO> create(@RequestBody @Valid AccountCreateDTO account) {
         return ResponseEntity.ok(accountService.create(account));
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
