package com.jotapem.youraccount.models.dto;

import com.jotapem.youraccount.validations.constraints.OfAgeConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@Schema(description = "Object used to create the owner.")
public class OwnerCreateDTO {

    @NotBlank
    @Size(max = 40)
    @Schema(description = "Owner's first name.", example = "Michael")
    private String firstName;

    @NotBlank
    @Size(max = 40)
    @Schema(description = "Owner's last name.", example = "Ross")
    private String lastName;

    @NotBlank
    @Size(max = 40)
    @Schema(description = "Owner's nickname.", example = "Mike")
    private String nickname;

    @NotNull
    @OfAgeConstraint
    @Schema(description = "Owner's birth date.", example = "2001-02-03")
    private LocalDate birthDate;

    @Email
    @Size(max = 60)
    @Schema(description = "Owner's email.", example = "mike007@gmail.com", required = true)
    private String email;

    @NotBlank
    @Size(max = 14)
    @Schema(description = "Owner's telephone.", example = "85990907755")
    private String telephone;
}
