package com.jotapem.youraccount.controllers.docs;

import com.jotapem.youraccount.models.dto.PageResultDTO;
import com.jotapem.youraccount.models.dto.account.AccountCreateDTO;
import com.jotapem.youraccount.models.dto.account.AccountDetailsDTO;
import com.jotapem.youraccount.models.dto.account.AccountUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AccountControllerDocs {

    @Operation(summary = "Create account operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDetailsDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Client error",
                    content = @Content)
    })
    AccountDetailsDTO create(AccountCreateDTO account);

    @Operation(summary = "Account list operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account list returned successfully")
    })
    ResponseEntity<PageResultDTO<AccountDetailsDTO>> getAll(Pageable pageable);

    @Operation(summary = "Update account operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Client error",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    void update(UUID id, AccountUpdateDTO account);


    @Operation(summary = "Delete account operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Client error",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    void deleteById(UUID id);

    ResponseEntity<AccountDetailsDTO> getById(UUID id);
}
