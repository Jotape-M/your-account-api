package com.jotapem.youraccount.models.dto;

import com.jotapem.youraccount.validations.constraints.OfAgeConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class OwnerCreateDTO {

    @NotBlank
    @Size(max = 40)
    private String firstName;

    @NotBlank
    @Size(max = 40)
    private String lastName;

    @NotBlank
    @Size(max = 40)
    private String nickname;

    @NotNull
    @OfAgeConstraint
    private LocalDate birthDate;

    @Email
    @Size(max = 60)
    private String email;

    @NotBlank
    @Size(max = 14)
    private String telephone;
}
