package com.jotapem.youraccount.validations;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import com.jotapem.youraccount.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;

@Component
@RequiredArgsConstructor
public class OwnerValidator {

    private final OwnerRepository ownerRepository;

    public void validateForCreation(OwnerCreateDTO ownerToCreate) {
        validateEmail(ownerToCreate.getEmail());
    }

    private void validateEmail(String email) {
        ownerRepository.findByEmail(email).ifPresent(Owner -> {
            throw new EntityExistsException("Account owner already registered with this email");
        });
    }
}
