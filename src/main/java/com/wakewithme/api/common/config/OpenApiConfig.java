package com.wakewithme.api.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WakeWithMe API")
                        .version("1.0")
                        .description("API documentation for WakeWithMe application")
                        .contact(new Contact()
                                .name("Csaba Marton")
                                .email("martoncsab@gmail.com")));
    }
}
