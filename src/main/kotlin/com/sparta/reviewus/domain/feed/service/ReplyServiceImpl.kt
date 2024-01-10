package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.exception.reply.WrongPasswordException
import com.sparta.reviewus.domain.feed.dto.AddReplyRequest
import com.sparta.reviewus.domain.feed.dto.DeleteReplyRequest
import com.sparta.reviewus.domain.feed.dto.ReplyResponse
import com.sparta.reviewus.domain.feed.dto.UpdateReplyRequest
import com.sparta.reviewus.domain.feed.model.Reply
import com.sparta.reviewus.domain.feed.model.toResponse
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.feed.repository.ReplyRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReplyServiceImpl(
    private val feedRepository: FeedRepository,
    private val replyRepository: ReplyRepository,
) : ReplyService {
    @Transactional
    override fun addReply(
        feedId: Long,
        addReplyRequest: AddReplyRequest,
    ): ReplyResponse {
        val feed = feedRepository.findByIdOrNull(feedId)
            ?: throw ModelNotFoundException("feed", feedId)

        val reply = Reply(
            password = addReplyRequest.password,
            content = addReplyRequest.content,
            feed = feed
        )
        return replyRepository.save(reply).toResponse()
    }

    @Transactional
    override fun updateReply(
        feedId: Long,
        replyId: Long,
        request: UpdateReplyRequest,
    ): ReplyResponse {
        val reply = replyRepository.findByFeedIdAndId(feedId, replyId)
            ?: throw ModelNotFoundException("Reply", replyId)

        if (reply.password != request.password)
            throw WrongPasswordException()
        else {
            reply.content = request.content
        }
        return replyRepository.save(reply).toResponse()
    }

    @Transactional
    override fun deleteReply(
        feedId: Long,
        replyId: Long,
        request: DeleteReplyRequest,
    ) {
        val reply = replyRepository.findByFeedIdAndId(feedId, replyId)
            ?: throw ModelNotFoundException("Reply", replyId)

        if (reply.password != request.password)
            throw WrongPasswordException()
        else {
            replyRepository.delete(reply)
        }
    }
}