package com.sparta.reviewus.auth

import com.sparta.reviewus.auth.service.AuthService
import com.sparta.reviewus.common.Base64Util
import com.sparta.reviewus.auth.exception.AuthenticationException
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class BasicAuthHandlerMethodArgumentResolver(
    private val authService: AuthService
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.parameterType == AuthenticatedMember::class.java
    }

    override fun resolveArgument(
        methodParameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): AuthenticatedMember {
        val (email, password) =
            webRequest.getHeader("Authorization")
                ?.let { Base64Util.decodeBasicAuth(it) } ?: throw AuthenticationException("잘못된 인증 방식입니다.")

        return authService.authenticateMember(email, password)
    }
}