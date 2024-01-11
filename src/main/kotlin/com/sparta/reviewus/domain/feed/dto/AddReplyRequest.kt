package com.sparta.reviewus.domain.feed.dto

data class AddReplyRequest(
    val memberId: Long,
    val content: String,
    val password: String,
)