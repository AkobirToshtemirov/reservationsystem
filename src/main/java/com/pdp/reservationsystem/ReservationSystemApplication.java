package com.pdp.reservationsystem;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationSystemApplication.class, args);
    }

    @Bean
    public GroupedOpenApi adminOpenApi() {
        return GroupedOpenApi.builder()
                .group("admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi agentOpenApi() {
        return GroupedOpenApi.builder()
                .group("agent")
                .pathsToMatch("/agents/**")
                .build();
    }

    @Bean
    public GroupedOpenApi customerOpenApi() {
        return GroupedOpenApi.builder()
                .group("customer")
                .pathsToMatch("/customer/**")
                .build();
    }

    @Bean
    public SecurityScheme securityScheme() {
        return new SecurityScheme()
                .name("bearerAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }

    @Bean
    public SecurityRequirement securityRequirement() {
        return new SecurityRequirement().addList("bearerAuth");
    }

    @Bean
    public SpringDocConfigProperties springDocConfigProperties() {
        return new SpringDocConfigProperties();
    }

    @Bean
    public io.swagger.v3.oas.models.OpenAPI customOpenAPI() {
        return new io.swagger.v3.oas.models.OpenAPI()
                .info(new Info().title("Airline Reservation System API")
                        .description("APIs for managing airline reservations")
                        .version("1.0.0")
                        .contact(new Contact().name("Your Name").email("your@email.com"))
                )
                .addServersItem(new Server().url("http://localhost:8080").description("Local Server"));
    }
}
