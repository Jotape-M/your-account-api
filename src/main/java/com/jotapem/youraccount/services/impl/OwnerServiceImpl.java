package com.jotapem.youraccount.services.impl;

import com.jotapem.youraccount.mappers.OwnerMapper;
import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import com.jotapem.youraccount.models.entities.Owner;
import com.jotapem.youraccount.repositories.OwnerRepository;
import com.jotapem.youraccount.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    private final OwnerMapper ownerMapper;

    @Override
    public Owner create(OwnerCreateDTO owner) {
        Owner ownerToCreate = ownerMapper.toOwnerEntity(owner);
        return ownerRepository.save(ownerToCreate);
    }
}
