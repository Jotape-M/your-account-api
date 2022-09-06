package com.jotapem.youraccount.models.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AccountCreateDTO {
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate birthday;
    private String email;
    private String telephone;
    private BigDecimal balance;
}
