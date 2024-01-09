package com.sparta.reviewus.member.dto

import jakarta.validation.constraints.Email

data class JoinRequest (
    @field:Email(message = "제대로 된 이메일 형식을 입력해주세요.")
    val email: String,
    val password: String,
    val name: String,
    val nickname: String
)
