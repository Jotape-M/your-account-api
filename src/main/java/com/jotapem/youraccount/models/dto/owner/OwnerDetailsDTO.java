package com.jotapem.youraccount.models.dto.owner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Schema(description = "Object used to view the owner.")
public class OwnerDetailsDTO {

    @Schema(description = "Owner's id.")
    private UUID id;

    @Schema(description = "Owner's first name.", example = "Michael")
    private String firstName;

    @Schema(description = "Owner's last name.", example = "Ross")
    private String lastName;

    @Schema(description = "Owner's nickname.", example = "Mike")
    private String nickname;

    @Schema(description = "Owner's birth date.", example = "2001-02-03")
    private LocalDate birthDate;

    @Schema(description = "Owner's email.", example = "mike007@gmail.com")
    private String email;

    @Schema(description = "Owner's telephone.", example = "85990907755")
    private String telephone;
}
