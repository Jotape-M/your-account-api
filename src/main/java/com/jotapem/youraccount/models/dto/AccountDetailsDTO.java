package com.jotapem.youraccount.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccountDetailsDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate birthday;
    private String email;
    private String telephone;
    private BigDecimal balance;
}
