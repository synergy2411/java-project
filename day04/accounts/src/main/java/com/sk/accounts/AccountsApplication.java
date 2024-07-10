package com.sk.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Tag(
        name = "Accounts Microservice for Synergy Bank",
        description = "Create Read Update and Delete operation for Accounts Microservice",
        externalDocs = @ExternalDocumentation(
                url = "http://www.synergybank.com",
                description = "External Document for Accounts Microservice"
        )
)
@OpenAPIDefinition
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@SpringBootApplication
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
