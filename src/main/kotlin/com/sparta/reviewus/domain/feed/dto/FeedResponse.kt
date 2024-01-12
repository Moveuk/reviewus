package com.sparta.reviewus.domain.feed.dto

import com.sparta.reviewus.domain.feed.model.Category

data class FeedResponse(
    val id : Long,
    val nickname : String,
    val category : Category,
    val title : String,
    val feedPicUrl : String,
    val longitude : String?,
    val latitude : String?,
    val description : String?
)

