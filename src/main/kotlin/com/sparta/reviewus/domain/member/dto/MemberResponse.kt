package com.sparta.reviewus.domain.member.dto

data class MemberResponse(
    val id: Long,
    val email: String,
    val name: String,
    val profilePicUrl: String,
    val nickname: String,
    val introduction: String,
    val address: String?,
    val interest: String?,
)
