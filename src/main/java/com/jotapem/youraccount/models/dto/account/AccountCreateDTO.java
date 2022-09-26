package com.jotapem.youraccount.models.dto.account;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Object used to create the account.")
public class AccountCreateDTO {

    @Size(min = 4, max = 4)
    @Schema(description = "Account's agency.", example = "1360")
    private String agency;

    @NotNull
    @Schema(description = "Account's balance.", example = "65.90")
    private BigDecimal balance;

    @Valid
    private OwnerCreateDTO owner;
}
