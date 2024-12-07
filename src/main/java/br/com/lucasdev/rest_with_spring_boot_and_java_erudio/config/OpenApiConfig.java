package br.com.lucasdev.rest_with_spring_boot_and_java_erudio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RESTful API Java 17 and Spring Boot 3.4.0")
                        .version("v2")
                        .description("API developed at the curse")
                        .termsOfService("https://projetolucasdev.com.br/curso")
                        .license(new License().name("Apache 2.0")
                                .url("https://projetolucasdev.com.br/curso"))
                );
    }
}
