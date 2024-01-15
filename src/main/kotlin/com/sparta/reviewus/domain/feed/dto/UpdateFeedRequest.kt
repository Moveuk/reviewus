package com.sparta.reviewus.domain.feed.dto

import com.sparta.reviewus.domain.feed.model.Category

data class UpdateFeedRequest(
    val title : String,
    val description : String,
    val category : Category,
    val feedPicUrls : String,
    val longitude : String,
    val latitude : String
)
