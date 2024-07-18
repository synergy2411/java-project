package com.sk.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;


@SpringBootApplication
public class GatewayserverApplication {

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

		return routeLocatorBuilder
				.routes()
				.route(p -> p
						.path("/synergybank/accounts/**")
						.filters(f -> f.rewritePath("/synergybank/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS")
				)
				.route(p -> p
						.path("/synergybank/loans/**")
						.filters(f -> f.rewritePath("/synergybank/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS")
				)
				.route(p -> p
						.path("/synergybank/cards/**")
						.filters(f -> f.rewritePath("/synergybank/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS")
				)
				.build();


	}


	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

}
