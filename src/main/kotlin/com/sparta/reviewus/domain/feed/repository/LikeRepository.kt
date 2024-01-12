package com.sparta.reviewus.domain.feed.repository

import com.sparta.reviewus.domain.feed.model.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long>{

    fun existsByMemberIdAndFeedId(memberId: Long, feedId: Long): Boolean
    fun deleteByMemberIdAndFeedId(memberId: Long, feedId: Long): Int
    fun countByFeedId(feedId: Long): Int
}