package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.models.entities.Account;
import com.jotapem.youraccount.repositories.AccountRepository;
import com.jotapem.youraccount.repositories.OwnerRepository;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.RefreshMode.AFTER_EACH_TEST_METHOD;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase(refresh = AFTER_EACH_TEST_METHOD, type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
class AccountControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void contextLoads() {
        Assertions.assertEquals(true, accountRepository.existsById(UUID.fromString("01ee0bcb-4dd0-441e-a762-95b24c90ae88")));
    }

    @Test
    void createOneAccount() throws Exception {
        Assertions.assertEquals(1, accountRepository.count());
        Assertions.assertEquals(1, ownerRepository.count());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"agency\": \"9999\",\n" +
                        "  \"balance\": 65.9,\n" +
                        "  \"owner\": {\n" +
                        "    \"firstName\": \"Michael\",\n" +
                        "    \"lastName\": \"Ross\",\n" +
                        "    \"nickname\": \"Mike\",\n" +
                        "    \"birthDate\": \"2001-02-03\",\n" +
                        "    \"email\": \"mike007@gmail.com\",\n" +
                        "    \"telephone\": \"85990907755\"\n" +
                        "  }\n" +
                        "}")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.number").isNotEmpty())
                .andExpect(jsonPath("$.agency").value("9999"))
                .andExpect(jsonPath("$.owner.email").value("mike007@gmail.com"))
                .andReturn();

        Assertions.assertEquals(2, accountRepository.count());
        Assertions.assertEquals(2, ownerRepository.count());

        Account account = accountRepository.findAll().stream().filter(x->"9999".equals(x.getAgency())).findFirst().get();
        Assertions.assertEquals("mike007@gmail.com", account.getOwner().getEmail());
    }


    @Test
    @Sql("/script/account/0001_prepare_db_to_delete_account.sql")
    void deleteOneAccountCreated() throws Exception {
        Assertions.assertEquals(2, accountRepository.count());
        Assertions.assertEquals(2, ownerRepository.count());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/accounts/3615aa6d-1d97-46f7-9f11-4d4d3cc25782")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn();

        Assertions.assertEquals(1, accountRepository.count());
        Assertions.assertEquals(1, ownerRepository.count());

        Assertions.assertFalse(accountRepository.existsById(UUID.fromString("3615aa6d-1d97-46f7-9f11-4d4d3cc25782")));

    }

}