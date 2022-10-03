package com.jotapem.youraccount.repository;

import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.models.entities.Owner;
import com.jotapem.youraccount.repositories.AccountRepository;
import com.jotapem.youraccount.repositories.OwnerRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_EACH_TEST_METHOD;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureEmbeddedDatabase(refresh = AFTER_EACH_TEST_METHOD, type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void shouldBeSaveAnAccountSuccessful() {
        Account accountToCreate = new Account(
                "13123131-1",
                "0001",
                BigDecimal.valueOf(100.00),
                new Owner()
        );

        Account accountCreated = accountRepository.save(accountToCreate);

        assertThat(accountCreated).isNotNull();
        assertThat(accountCreated.getId()).isNotNull();
    }

    @Test
    void shouldBeUpdateAnAccountSuccessful() {
        Account accountToCreate = new Account(
                "13123131-1",
                "0001",
                BigDecimal.valueOf(100.00),
                new Owner()
        );

        Account accountCreated = accountRepository.save(accountToCreate);

        accountCreated.setAgency("0002");

        Account accountUpdated = accountRepository.save(accountCreated);

        assertThat(accountUpdated).isNotNull();
        assertThat(accountUpdated.getAgency()).isEqualTo(accountCreated.getAgency());
    }

    @Test
    void shouldBeReturnAnAccountPageNotEmpty() {
        Owner ownerToCreate = new Owner(
                "Louis",
                "Lit",
                "LoLit",
                LocalDate.of(2000, 7, 3),
                "louis005@gmail.com",
                "85912345678"
        );

        Owner ownerCreated = ownerRepository.save(ownerToCreate);

        Account accountToCreate = new Account(
                "13123131-1",
                "0001",
                BigDecimal.valueOf(100.00),
                ownerCreated
                );

        accountRepository.save(accountToCreate);

        Pageable pageable = PageRequest.of(0, 20);

        Page<Account> accountsPage = accountRepository.findAll(pageable);

        assertThat(accountsPage.getContent()).isNotEmpty();
    }

    @Test
    void shouldBeReturnAnAccountPageEmpty() {
        Pageable pageable = PageRequest.of(0, 20);

        Page<Account> accountsPage = accountRepository.findAll(pageable);

        assertThat(accountsPage.getContent()).isEmpty();
    }

    @Test
    void shouldBeReturnAnAccountById() {
        Account accountToCreate = new Account(
                "13123131-1",
                "0001",
                BigDecimal.valueOf(100.00),
                new Owner()
        );

        Account accountCreated = accountRepository.save(accountToCreate);
        Optional<Account> accountFound = accountRepository.findById(accountCreated.getId());

        assertThat(accountFound).isNotEmpty();
    }

    @Test
    void shouldBeDeleteAnAccountSuccessful() {
        Account accountToCreate = new Account(
                "13123131-1",
                "0001",
                BigDecimal.valueOf(100.00),
                new Owner()
        );

        Account accountCreated = accountRepository.save(accountToCreate);

        accountRepository.delete(accountCreated);

        Optional<Account> accountFound = accountRepository.findById(accountCreated.getId());

        assertThat(accountFound).isEmpty();
    }
}
