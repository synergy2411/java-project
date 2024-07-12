package com.sk.gatewayserver.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.authorizeExchange(exchange -> exchange
                .pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers("/synergybank/accounts/**").authenticated()
                .pathMatchers("/synergybank/loans/**").authenticated()
                .pathMatchers("/synergybank/cards/**").authenticated()
                ).oauth2ResourceServer(config -> config.jwt(Customizer.withDefaults()));

        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);

        return httpSecurity.build();
    }
}
