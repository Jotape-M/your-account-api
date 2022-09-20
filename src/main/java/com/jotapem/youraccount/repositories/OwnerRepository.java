package com.jotapem.youraccount.repositories;

import com.jotapem.youraccount.models.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, UUID> {

    Optional<Owner> findByEmail(String email);
}
