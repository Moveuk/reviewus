package com.sparta.reviewus.domain.member.dto

import jakarta.validation.constraints.Email

data class LoginRequest (
    @field:Email(message = "제대로 된 이메일 형식을 입력해주세요.")
    val email: String,
    val password: String,
)
