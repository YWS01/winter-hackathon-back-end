package com.hackathon.otj_logger.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI otjOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OTJ Logger API")
                        .description("API for OTJ Logger backend. Endpoints for journal entries, timesheets, apprentices, coaches, courses, categories, KSBs and holidays.")
                        .version("v1.0.0")
                        .contact(new Contact().name("OTJ Team").email("dev@example.com"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project README")
                        .url("https://example.com/docs"));
    }
}

