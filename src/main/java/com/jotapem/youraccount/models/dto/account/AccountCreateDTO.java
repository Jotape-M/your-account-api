package com.jotapem.youraccount.models.dto.account;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
public class AccountCreateDTO {

    @Valid
    private OwnerCreateDTO owner;
}
