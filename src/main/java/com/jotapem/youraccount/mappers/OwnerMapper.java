package com.jotapem.youraccount.mappers;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import com.jotapem.youraccount.models.entities.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    Owner toOwnerEntity(OwnerCreateDTO ownerCreateDTO);
}
