package com.jotapem.youraccount.controllers.docs;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;

import java.util.UUID;

public interface OwnerControllerDocs {

    void update(UUID id, OwnerCreateDTO ownerCreateDTO);
}
