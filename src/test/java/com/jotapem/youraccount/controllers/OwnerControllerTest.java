package com.jotapem.youraccount.controllers;

import com.jotapem.youraccount.models.entities.Owner;
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
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase(refresh = AFTER_EACH_TEST_METHOD, type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES)
class OwnerControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    @Sql("/script/owners/0001_test_filter_by_email.sql")
    void filterByEmail() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/owners")
                .param("email", "mike007@gmail.com")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.elements").isArray())
                .andExpect(jsonPath("$.elements",hasSize(1)))
                .andExpect(jsonPath("$.elements[0].id").isNotEmpty())
                .andExpect(jsonPath("$.elements[0].email").value("mike007@gmail.com"))
                .andExpect(jsonPath("$.numberElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andReturn();


        Assertions.assertEquals(2, ownerRepository.count());
    }

    @Test
    @Sql("/script/owners/0002_test_filter_by_nickname_and_lastname.sql")
    void filterByNickNameAndLastName() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/owners")
                .param("nickname", "Mike")
                .param("lastName", "Costa")
                .param("logicOperator", "AND")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.elements").isArray())
                .andExpect(jsonPath("$.elements",hasSize(1)))
                .andExpect(jsonPath("$.elements[0].id").isNotEmpty())
                .andExpect(jsonPath("$.elements[0].nickname").value("Mike"))
                .andExpect(jsonPath("$.elements[0].lastName").value("Costa"))
                .andExpect(jsonPath("$.numberElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andReturn();

        Assertions.assertEquals(3, ownerRepository.count());


    }

    @Test
    @Sql("/script/owners/0003_test_filter_by_nickname_or_lastname.sql")
    void filterByNickNameOrLastName() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/owners")
                .param("nickname", "Mike")
                .param("lastName", "Costa")
                .param("logicOperator", "OR")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.elements").isArray())
                .andExpect(jsonPath("$.elements",hasSize(2)))
                .andExpect(jsonPath("$.numberElements").value(2))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andReturn();

        Assertions.assertEquals(3, ownerRepository.count());

    }


    @Test
    void updateOneOwnerCreated() throws Exception {
        String ownerId = "16a0aec3-4e35-403f-886d-ab4e83da40cb";

        Assertions.assertEquals(1, ownerRepository.count());
        Assertions.assertTrue(ownerRepository.existsById(UUID.fromString(ownerId)));

        Owner ownerCreated = ownerRepository.findById(UUID.fromString(ownerId)).get();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/owners/" + ownerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\": \"Michael\",\n" +
                        "    \"lastName\": \"Ross\",\n" +
                        "    \"nickname\": \"Mike007\",\n" +
                        "    \"birthDate\": \"2001-02-03\",\n" +
                        "    \"email\": \"mike007@gmail.com\",\n" +
                        "    \"telephone\": \"85990907755\"\n" +
                        "  }\n")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn();

        Owner ownerUpdated = ownerRepository.findById(UUID.fromString(ownerId)).get();

        Assertions.assertNotEquals(ownerCreated.getUpdatedAt(), ownerUpdated.getUpdatedAt());
        Assertions.assertNotEquals(ownerCreated.getNickname(), ownerUpdated.getNickname());
        Assertions.assertEquals("Mike007", ownerUpdated.getNickname());
    }
}