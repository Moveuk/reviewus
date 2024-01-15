package com.sparta.reviewus.auth.config

import com.sparta.reviewus.auth.BasicAuthHandlerMethodArgumentResolver
import com.sparta.reviewus.auth.JWTAuthHandlerMethodArgumentResolver
import com.sparta.reviewus.auth.service.AuthService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfigure(
    private val authService: AuthService
): WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver?>) {
        resolvers.add(jwtAuthHandlerMethodArgumentResolver())
    }

    @Bean
    fun basicAuthHandlerMethodArgumentResolver(): BasicAuthHandlerMethodArgumentResolver {
        return BasicAuthHandlerMethodArgumentResolver(authService)
    }

    @Bean
    fun jwtAuthHandlerMethodArgumentResolver(): JWTAuthHandlerMethodArgumentResolver {
        return JWTAuthHandlerMethodArgumentResolver(authService)
    }
}