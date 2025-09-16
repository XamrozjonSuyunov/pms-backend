package com.pms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "PMS Support",
                        email = "info@pms.com",
                        url = "pms.com"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "PMS API Documentation",
                version = "1.0.0",
                license = @License(
                        name = "PMS Docs",
                        url = "https://pms.com"
                ),
                termsOfService = "Terms of services"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
