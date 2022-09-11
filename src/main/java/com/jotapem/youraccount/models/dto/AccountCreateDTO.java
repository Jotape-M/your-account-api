package com.jotapem.youraccount.models.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class AccountDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String nickname;

    @NotNull
    private LocalDate birthday;

    @Email
    private String email;

    @NotNull
    private String telephone;
}
