package com.jotapem.youraccount.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String API_TITLE = "YourAccount API";
    private static final String API_DESCRIPTION = "Project YourAccount API";
    private static final String API_VERSION = "1.0.0";
    private static final String CONTACT_URL = "https://github.com/Jotape-M";
    private static final String CONTACT_EMAIL = "joaopedro.m2507@gmail.com";


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(buildCustomInfo());
    }

    private Info buildCustomInfo() {
        return new Info()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .contact(buildCustomContact());
    }

    private Contact buildCustomContact() {
        return new Contact()
                .email(CONTACT_EMAIL)
                .url(CONTACT_URL);
    }
}
