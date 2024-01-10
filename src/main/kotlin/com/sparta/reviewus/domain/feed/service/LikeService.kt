package com.sparta.reviewus.domain.feed.service

interface LikeService {

    fun like(memberId: Long, feedId: Long): Boolean

}
