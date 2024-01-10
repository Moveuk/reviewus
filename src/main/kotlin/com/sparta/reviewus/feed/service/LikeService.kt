package com.sparta.reviewus.feed.service

interface LikeService {

    fun like(memberId: Long, feedId: Long): Boolean

}
