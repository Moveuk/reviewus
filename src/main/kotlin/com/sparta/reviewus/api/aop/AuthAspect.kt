package com.sparta.reviewus.api.aop

import com.sparta.reviewus.auth.exception.AuthenticationException
import com.sparta.reviewus.auth.exception.ExpiredJwtException
import com.sparta.reviewus.common.JWTUtil
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


/**
 * [Auth] 어노테이션이 붙은 메소드나 클래스에 대해 인증을 검사하는 클래스입니다.
 */
@Aspect
@Component
class AuthAspect() {
    /**
     * [Auth] 어노테이션이 붙은 곳을 [org.aspectj.lang.annotation.Pointcut]으로 인터셉트합니다.
     *
     * 해당 포인트 컷이 실행되기 전([Before]에 아래 메서드를 실행합니다.
     *
     * @param auth 인터셉트 된 해당 [Auth] - Level을 알아낼 수 있습니다.
     */
    @Before("@annotation(com.sparta.reviewus.api.aop.IsAuthenticated)")
    fun authToken() {
        /**
         * 현재 인터셉트 된 서블릿의 [HttpServletRequest]를 가져옵니다.
         */
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes)
            .request

        val authorizationHeader =
            request.getHeader("Authorization") ?: throw AuthenticationException("잘못된 인증 방식입니다.")

        JWTUtil.getTokenFromAuthorizationHeader(authorizationHeader)
            .let { token ->
                if (!JWTUtil.isValidToken(token)) throw ExpiredJwtException("만료된 JWT 토큰입니다. 다시 로그인 해주세요.")
            }
    }
}