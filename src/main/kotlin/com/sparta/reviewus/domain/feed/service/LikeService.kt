package com.sparta.reviewus.domain.feed.service

interface LikeService {

    fun like(memberId: Long, feedId: Long): Boolean

    fun countLikes(feedId: Long): Int

    fun isLiked(memberId: Long, feedId: Long): Boolean
}
