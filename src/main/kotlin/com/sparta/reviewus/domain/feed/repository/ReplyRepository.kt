package com.sparta.reviewus.domain.feed.repository

import com.sparta.reviewus.domain.feed.model.Reply
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository: JpaRepository<Reply, Long> {

    fun findByFeedIdAndId(feedId: Long, replyId: Long): Reply?

    fun findByFeedIdAndParentIsNull(feedId: Long): List<Reply>
}

