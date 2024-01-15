package com.sparta.reviewus.domain.feed.dto

data class AddReplyRequest(
    val parentId: Long,
    val content: String,
    val password: String,
)