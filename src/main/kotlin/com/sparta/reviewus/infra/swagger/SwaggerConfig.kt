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
            Components().addSecuritySchemes("basicAuth", basicAuth())
        )
        .addSecurityItem(securityItem())
        .info(
            Info()
                .title("ReviewUs API")
                .description("ReviewUs API schema")
                .version("0.0.0")
        )

    private fun basicAuth() = SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")

    private fun securityItem() = SecurityRequirement().addList("basicAuth")
}