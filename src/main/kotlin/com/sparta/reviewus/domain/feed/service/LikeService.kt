package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.member.dto.AuthenticatedMember

interface LikeService {

    fun like(authenticatedMember: AuthenticatedMember, feedId: Long): Boolean

    fun countLikes(feedId: Long): Int

    fun isLiked(memberId: Long, feedId: Long): Boolean
}
