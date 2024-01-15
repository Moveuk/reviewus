package com.sparta.reviewus.auth.exception

data class BadCredentialsException(
    override val message: String = "이메일 또는 비밀번호가 일치하지 않습니다. 다시 확인해 주세요."
) :
    AuthenticationException(message)
