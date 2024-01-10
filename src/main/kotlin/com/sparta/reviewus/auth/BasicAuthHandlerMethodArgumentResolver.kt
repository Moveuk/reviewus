package com.sparta.reviewus.auth

import com.sparta.reviewus.common.Base64AuthUtil
import com.sparta.reviewus.domain.exception.member.AuthenticationException
import com.sparta.reviewus.domain.member.dto.BasicAuthMember
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class BasicAuthHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(methodParameter: MethodParameter): Boolean {
        return methodParameter.parameterType == BasicAuthMember::class.java
    }
    override fun resolveArgument(
        methodParameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): BasicAuthMember {
        val (email, password) =
            webRequest.getHeader("Authorization")
                ?.let { Base64AuthUtil().decodeBasicAuth(it) } ?: throw AuthenticationException("잘못된 인증 방식입니다.")

        return BasicAuthMember(email, password)
    }
}