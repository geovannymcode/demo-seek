package com.geovannycode.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPI3Configuration {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Seek Service APIs")
                        .description("Demo test seek APIs")
                        .version("v1.0.0")
                        .contact(new Contact().name("GeovannyCode").email("me@geovannycode.com")));
    }
}
