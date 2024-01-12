package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.AccessDeniedException
import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.exception.WrongPasswordException
import com.sparta.reviewus.domain.feed.dto.AddReplyRequest
import com.sparta.reviewus.domain.feed.dto.DeleteReplyRequest
import com.sparta.reviewus.domain.feed.dto.ReplyResponse
import com.sparta.reviewus.domain.feed.dto.UpdateReplyRequest
import com.sparta.reviewus.domain.feed.model.Reply
import com.sparta.reviewus.domain.feed.model.toResponse
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.feed.repository.ReplyRepository
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReplyServiceImpl(
    private val feedRepository: FeedRepository,
    private val replyRepository: ReplyRepository,
) : ReplyService {
    @Transactional
    override fun addReply(
        feedId: Long,
        addReplyRequest: AddReplyRequest,
        authenticatedMember: AuthenticatedMember
    ): ReplyResponse {
        val feed = feedRepository.findByIdOrNull(feedId)
            ?: throw ModelNotFoundException("feed", feedId)
        val parentReply = replyRepository.findByIdOrNull(addReplyRequest.parentId)

        val reply = Reply(
            password = addReplyRequest.password,
            content = addReplyRequest.content,
            feed = feed,
            parent = parentReply,
            member = authenticatedMember.toMember()
        )
        return replyRepository.save(reply).toResponse()
    }

    @Transactional
    override fun updateReply(
        feedId: Long,
        replyId: Long,
        request: UpdateReplyRequest,
        authenticatedMember: AuthenticatedMember
    ): ReplyResponse {
        val reply = replyRepository.findByFeedIdAndId(feedId, replyId)
            ?: throw ModelNotFoundException("Reply", replyId)

        if (reply.member.id != authenticatedMember.id) throw AccessDeniedException()
        if (reply.password != request.password)
            throw WrongPasswordException()

        reply.content = request.content
        reply.modifiedDate = LocalDateTime.now()

        return replyRepository.save(reply).toResponse()
    }

    @Transactional
    override fun deleteReply(
        feedId: Long,
        replyId: Long,
        request: DeleteReplyRequest,
        authenticatedMember: AuthenticatedMember
    ) {
        val reply = replyRepository.findByFeedIdAndId(feedId, replyId)
            ?: throw ModelNotFoundException("Reply", replyId)


        if (reply.member.id != authenticatedMember.id) throw AccessDeniedException()
        if (reply.password != request.password) throw WrongPasswordException()

        replyRepository.delete(reply)
    }
}