package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.controllers.docs.OwnerControllerDocs;
import com.jotapem.youraccount.models.dto.PageResultDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerCreateDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerDetailsDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerFilterDto;
import com.jotapem.youraccount.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController implements OwnerControllerDocs {

    private final OwnerService ownerService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PageResultDTO<OwnerDetailsDTO>> getPaged(OwnerFilterDto filterDto){
        PageResultDTO<OwnerDetailsDTO> result = ownerService.getPaged(filterDto);
        return ResponseEntity.ok(result);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") UUID id, @RequestBody OwnerCreateDTO ownerCreateDTO) {
        ownerService.update(id, ownerCreateDTO);
    }
}
