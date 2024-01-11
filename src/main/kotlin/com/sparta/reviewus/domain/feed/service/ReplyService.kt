package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.feed.dto.AddReplyRequest
import com.sparta.reviewus.domain.feed.dto.DeleteReplyRequest
import com.sparta.reviewus.domain.feed.dto.ReplyResponse
import com.sparta.reviewus.domain.feed.dto.UpdateReplyRequest

interface ReplyService {

    fun addReply(
        feedId: Long,
        addReplyRequest: AddReplyRequest
    ): ReplyResponse

    fun updateReply(
        feedId: Long,
        replyId: Long,
        request: UpdateReplyRequest
    ): ReplyResponse

    fun deleteReply(feedId: Long,replyId: Long, request: DeleteReplyRequest)
}