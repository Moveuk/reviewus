package com.sparta.reviewus.auth

import com.sparta.reviewus.auth.exception.ExpiredJwtException
import com.sparta.reviewus.auth.service.AuthService
import com.sparta.reviewus.common.JWTUtil
import com.sparta.reviewus.auth.exception.AuthenticationException
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class JWTAuthHandlerMethodArgumentResolver(
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
        val authorizationHeader =
            webRequest.getHeader("Authorization") ?: throw AuthenticationException("잘못된 인증 방식입니다.")

        return JWTUtil.getTokenFromAuthorizationHeader(authorizationHeader)
            .let { token ->
                if (!JWTUtil.isValidToken(token))
                    throw ExpiredJwtException("만료된 JWT 토큰입니다. 다시 로그인 해주세요.")
                JWTUtil.getUserIdFromToken(token)
            }.let { memberEmail ->
                authService.authenticateMemberByJWT(memberEmail)
            }
    }
}