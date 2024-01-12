package com.sparta.reviewus.domain.feed.dto

import com.sparta.reviewus.domain.feed.model.Category
import java.time.LocalDateTime

data class FeedByIdResponse (
    val id : Long,
    val nickname : String,
    val createDate : LocalDateTime,
    val category : Category,
    val title : String,
    val feedPicUrl : String,
    val longitude : String?,
    val latitude : String?,
    val description : String?,
    val likeCount: Int,
    val likedByCurrentUser: Boolean,
    val replies: List<ReplyResponse>
)