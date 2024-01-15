package com.sparta.reviewus.domain.exception

data class EmailVerificationFailedException(
    override val message: String = "이메일 인증이 실패하였습니다."
) : RuntimeException()