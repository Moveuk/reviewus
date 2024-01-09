package com.sparta.reviewus.feed.dto

data class CreateFeedRequest(
    val memberId : Long,
    val category : String,
    val title : String,
    val feedPicUrl : String,
    val longitude : String?,
    val latitude : String?,
    val description : String?
)
