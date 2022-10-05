package com.jotapem.youraccount.controllers.docs;

import com.jotapem.youraccount.models.dto.PageResultDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerCreateDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerDetailsDTO;
import com.jotapem.youraccount.models.dto.owner.OwnerFilterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface OwnerControllerDocs {

    @Operation(summary = "Owner list operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner list returned successfully")
    })
    ResponseEntity<PageResultDTO<OwnerDetailsDTO>> getPaged(@ParameterObject OwnerFilterDto filterDto);

    @Operation(summary = "Update owner operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Client error",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Owner not found")
    })
    void update(UUID id, OwnerCreateDTO ownerCreateDTO);
}
