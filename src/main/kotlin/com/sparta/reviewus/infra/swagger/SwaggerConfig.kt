package com.sparta.reviewus.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        .components(
            Components().addSecuritySchemes("bearerAuth", bearerAuth())
        )
        .addSecurityItem(bearerAuthSecurityItem())
        .info(
            Info()
                .title("ReviewUs API")
                .description("ReviewUs API schema")
                .version("0.0.0")
        )

    private fun basicAuth() = SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")

    private fun basicAuthSecurityItem() = SecurityRequirement().addList("basicAuth")

    private fun bearerAuth() = SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")

    private fun bearerAuthSecurityItem() = SecurityRequirement().addList("bearerAuth")
}