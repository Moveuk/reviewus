package com.sparta.reviewus.feed.repository

import com.sparta.reviewus.feed.model.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long>{

    fun findByMemberIdAndFeedId(memberId: Long, feedId: Long): Like?
    fun existsByMemberIdAndFeedId(memberId: Long, feedId: Long): Boolean
    fun deleteByMemberIdAndFeedId(memberId: Long, feedId: Long): Int
}