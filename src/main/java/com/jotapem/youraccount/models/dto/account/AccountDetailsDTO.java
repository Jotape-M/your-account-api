package com.jotapem.youraccount.models.dto.account;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AccountDetailsDTO {
    private UUID id;
    private String number;
    private String agency;
    private BigDecimal balance;
}
