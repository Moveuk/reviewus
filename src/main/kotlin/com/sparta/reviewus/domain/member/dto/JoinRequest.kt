package com.sparta.reviewus.domain.member.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.springframework.validation.annotation.Validated

@Validated
data class JoinRequest (
    @field:Email(message = "제대로 된 이메일 형식을 입력해주세요.")
    val email: String,

    @field:Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
        message = "비밀번호는 최소 8자 이상, 숫자, 문자, 특수문자를 포함해야 합니다. 공백은 포함하지 않습니다.")
    val password: String,

    val name: String,
    val nickname: String
)
