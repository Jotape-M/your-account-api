package com.jotapem.youraccount.models.dto.account;

import com.jotapem.youraccount.models.dto.owner.OwnerDetailsDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Object used to view the account.")
public class AccountDetailsDTO {

    @Schema(description = "Account's id.")
    private UUID id;

    @Schema(description = "Account's number.", example = "098713235-1")
    private String number;

    @Schema(description = "Account's agency.", example = "0916")
    private String agency;

    @Schema(description = "Account's balance.", example = "1350.00")
    private BigDecimal balance;

    @Schema(description = "Account's creation date.", example = "2022-10-01")
    private LocalDateTime createdAt;

    private OwnerDetailsDTO owner;
}
