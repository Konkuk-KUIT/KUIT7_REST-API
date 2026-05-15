package com.kuit.baemin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI baeminOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("배민 REST API")
                        .description("배민 REST API 문서")
                        .version("v1.0.0"));
    }
}
