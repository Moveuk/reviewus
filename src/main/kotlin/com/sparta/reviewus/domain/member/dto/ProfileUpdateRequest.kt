package com.sparta.reviewus.domain.member.dto

data class ProfileUpdateRequest(
    val id: Long,
    val name: String,
    val profilePicUrl: String,
    val nickname: String,
    val password: String,
    val introduction: String,
    val address: String,
    val interest: String,
)