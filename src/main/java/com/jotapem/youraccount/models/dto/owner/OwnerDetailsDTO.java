package com.jotapem.youraccount.models.dto.owner;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class OwnerDetailsDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate birthDate;
    private String email;
    private String telephone;
}
