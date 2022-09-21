package com.jotapem.youraccount.models.dto.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountUpdateDTO {

    @Size(min = 4, max = 4)
    private String agency;

    @NotNull
    private BigDecimal balance;
}
