package com.sparta.reviewus.domain.feed.dto

import java.time.LocalDateTime

data class ReplyResponse (
    val id: Long?,
    val content: String,
    val password: String,
    val createDate: LocalDateTime,
    val modifiedDate: LocalDateTime?
)