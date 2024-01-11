package com.sparta.reviewus.domain.member.dto

import com.sparta.reviewus.domain.member.model.Profile

data class AuthenticatedMember(
    val id: Long,
    val email: String,
    val name: String,
    val password: String,
    val profile: Profile
)