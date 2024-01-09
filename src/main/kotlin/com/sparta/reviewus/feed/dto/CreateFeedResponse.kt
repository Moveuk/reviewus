package com.sparta.reviewus.feed.dto

import com.sparta.reviewus.member.dto.MemberResponse

data class CreateFeedResponse(
    val id : Long,
    val member : MemberResponse,
    val category : String,
    val title : String,
    val feedPicUrl : String,
    val longitude : String?,
    val latitude : String?,
    val description : String?
)

