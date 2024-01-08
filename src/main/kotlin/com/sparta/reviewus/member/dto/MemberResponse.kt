package com.sparta.reviewus.member.dto

data class MemberResponse(
    val name: String,
    val profilePicUrl: String,
    val nickname: String,
    val introduction: String,
    val address: String,
    val interest: String,
)
