package com.kuit.baemin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "KUIT Baemin REST API",
                description = "KUIT 7th REST API mission documentation",
                version = "v1"
        )
)
public class OpenApiConfig {
}
