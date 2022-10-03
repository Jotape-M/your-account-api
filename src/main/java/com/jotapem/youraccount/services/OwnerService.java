package com.jotapem.youraccount.services;

import com.jotapem.youraccount.models.dto.PageResultDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerCreateDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerDetailsDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerFilterDto;
import com.jotapem.youraccount.models.entities.Owner;

import java.util.UUID;

public interface OwnerService {
    Owner create(OwnerCreateDTO owner);
    void update(UUID id, OwnerCreateDTO owner);

    PageResultDTO<OwnerDetailsDTO> getPaged(OwnerFilterDto filterDto);
}
