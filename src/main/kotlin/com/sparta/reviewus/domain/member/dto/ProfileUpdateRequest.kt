package com.sparta.reviewus.domain.member.dto

import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL
import org.springframework.validation.annotation.Validated

@Validated
data class ProfileUpdateRequest(

    val name: String,

    @field:URL(message = "유효한 URL 형식이어야 합니다.")
    val profilePicUrl: String,

    val nickname: String,

    @field:Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
        message = "비밀번호는 최소 8자 이상, 숫자, 문자, 특수문자를 포함해야 합니다. 공백은 포함하지 않습니다.")
    val password: String,

    @field:Size(max = 1000, message = "글의 길이는 1000자 이하 여야 합니다.")
    val introduction: String,

    val address: String,
    val interest: String,
)