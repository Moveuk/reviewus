package com.sparta.reviewus.domain.feed.dto

import com.sparta.reviewus.domain.feed.model.Category
import com.sparta.reviewus.domain.member.dto.MemberResponse

data class FeedResponse(
    val id : Long,
    val member : MemberResponse,
    val category : Category,
    val title : String,
    val feedPicUrl : String,
    val longitude : String?,
    val latitude : String?,
    val description : String?
)

