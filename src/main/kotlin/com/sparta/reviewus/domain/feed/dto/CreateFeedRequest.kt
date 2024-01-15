package com.sparta.reviewus.domain.feed.dto

import com.sparta.reviewus.domain.feed.model.Category

data class CreateFeedRequest(
    val category : Category,
    val title : String,
    val feedPicUrls : String,
    val longitude : String?,
    val latitude : String?,
    val description : String?
)
