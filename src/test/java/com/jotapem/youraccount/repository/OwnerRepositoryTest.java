package com.jotapem.youraccount.repository;

import com.jotapem.youraccount.models.entities.Owner;
import com.jotapem.youraccount.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldBeSaveAnOwnerSuccessful() {
        Owner ownerToCreate = new Owner(
                "Louis",
                "Lit",
                "LoLit",
                LocalDate.of(2000, 7, 3),
                "louis005@gmail.com",
                "85912345678"
        );

        Owner ownerCreated = ownerRepository.save(ownerToCreate);

        assertThat(ownerCreated).isNotNull();
        assertThat(ownerCreated.getId()).isNotNull();
    }

    @Test
    void shouldBeUpdateAnOwnerSuccessful() {
        Owner ownerToCreate = new Owner(
                "Louis",
                "Lit",
                "LoLit",
                LocalDate.of(2000, 7, 3),
                "louis005@gmail.com",
                "85912345678"
        );

        Owner ownerCreated = ownerRepository.save(ownerToCreate);

        ownerCreated.setNickname("LouLit");

        Owner ownerUpdated = ownerRepository.save(ownerCreated);

        assertThat(ownerUpdated).isNotNull();
        assertThat(ownerUpdated.getNickname()).isEqualTo(ownerCreated.getNickname());
    }


    @Test
    void shouldBeReturnAnOwnerByEmail() {
        Owner ownerToCreate = new Owner(
                "Louis",
                "Lit",
                "LoLit",
                LocalDate.of(2000, 7, 3),
                "louis005@gmail.com",
                "85912345678"
        );

        Owner ownerCreated = ownerRepository.save(ownerToCreate);

        Optional<Owner> ownerFound = ownerRepository.findByEmail(ownerCreated.getEmail());

        assertThat(ownerFound).isNotEmpty();
    }
}
