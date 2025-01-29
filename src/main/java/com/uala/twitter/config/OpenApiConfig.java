package com.uala.twitter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Twitter Uala",
                version = "1.0",
                description = "This is a Twitter Test"
        )
)
public class OpenApiConfig {
}
