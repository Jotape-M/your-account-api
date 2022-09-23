package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.controllers.docs.OwnerControllerDocs;
import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import com.jotapem.youraccount.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController implements OwnerControllerDocs {

    private final OwnerService ownerService;

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody OwnerCreateDTO ownerCreateDTO) {
        ownerService.update(id, ownerCreateDTO);
    }
}
