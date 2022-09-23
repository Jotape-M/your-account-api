package com.jotapem.youraccount.validations;

import com.jotapem.youraccount.models.dto.OwnerCreateDTO;
import com.jotapem.youraccount.models.entities.Owner;
import com.jotapem.youraccount.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OwnerValidator {

    private final OwnerRepository ownerRepository;

    public void validateForCreation(OwnerCreateDTO ownerToCreate) {
        validateEmail(ownerToCreate.getEmail());
    }

    public void validateForUpdate(Owner ownerBeforeUpdate, OwnerCreateDTO ownerToUpdate) {
        validateEmailForUpdate(ownerBeforeUpdate.getEmail(), ownerToUpdate.getEmail());
    }

    public Owner verifyAndGetIfExists(UUID id) {
        return ownerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Owner not found whit this " + id));
    }

    private void validateEmailForUpdate(String currentEmail, String newEmail) {
        if(!currentEmail.equals(newEmail)) validateEmail(newEmail);
    }

    private void validateEmail(String email) {
        ownerRepository.findByEmail(email).ifPresent(Owner -> {
            throw new EntityExistsException("Account owner already registered with this email");
        });
    }
}
