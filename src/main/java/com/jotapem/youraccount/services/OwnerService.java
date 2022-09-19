package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import com.jotapem.youraccount.models.entities.Owner;

public interface OwnerService {
    Owner create(OwnerCreateDTO owner);
}
