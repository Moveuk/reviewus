package com.sparta.reviewus.auth.config

import com.sparta.reviewus.auth.BasicAuthHandlerMethodArgumentResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfigure: WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver?>) {
        resolvers.add(basicAuthHandlerMethodArgumentResolver())
    }

    @Bean
    fun basicAuthHandlerMethodArgumentResolver(): BasicAuthHandlerMethodArgumentResolver {
        return BasicAuthHandlerMethodArgumentResolver()
    }
}