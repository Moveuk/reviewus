package com.sparta.reviewus.domain.feed.controller

import com.sparta.reviewus.api.aop.IsAuthenticated
import com.sparta.reviewus.domain.feed.dto.AddReplyRequest
import com.sparta.reviewus.domain.feed.dto.DeleteReplyRequest
import com.sparta.reviewus.domain.feed.dto.ReplyResponse
import com.sparta.reviewus.domain.feed.dto.UpdateReplyRequest
import com.sparta.reviewus.domain.feed.service.FeedService
import com.sparta.reviewus.domain.feed.service.ReplyService
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/feeds/{feedId}/replies")
class ReplyController(

    private val replyService: ReplyService,
    private val feedService: FeedService
) {
    @IsAuthenticated
    @PostMapping
    fun addReply(
        @PathVariable feedId: Long,
        @RequestBody addReplyRequest: AddReplyRequest,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ): ResponseEntity<ReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(replyService.addReply(feedId,addReplyRequest,authenticatedMember))
    }

    @IsAuthenticated
    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable feedId: Long,
        @PathVariable replyId: Long,
        @RequestBody updateReplyRequest: UpdateReplyRequest,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ): ResponseEntity<ReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(replyService.updateReply(feedId,replyId,updateReplyRequest,authenticatedMember))
    }

    @IsAuthenticated
    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @PathVariable feedId: Long,
        @PathVariable replyId: Long,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember,
        deleteReplyRequest: DeleteReplyRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(replyService.deleteReply(feedId,replyId,deleteReplyRequest,authenticatedMember))
    }
}