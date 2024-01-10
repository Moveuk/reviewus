package com.sparta.reviewus.domain.feed.controller.ReplyController

import com.sparta.reviewus.domain.feed.dto.AddReplyRequest
import com.sparta.reviewus.domain.feed.dto.DeleteReplyRequest
import com.sparta.reviewus.domain.feed.dto.ReplyResponse
import com.sparta.reviewus.domain.feed.dto.UpdateReplyRequest
import com.sparta.reviewus.domain.feed.service.ReplyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/feeds/{feedId}/replies")
class ReplyController(

    private val replyService: ReplyService
) {
    @PostMapping
    fun addReply(
        @PathVariable feedId: Long,
        @RequestBody addReplyRequest: AddReplyRequest
    ): ResponseEntity<ReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(replyService.addReply(feedId,addReplyRequest))
    }

    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable feedId: Long,
        @PathVariable replyId: Long,
        @RequestBody updateReplyRequest: UpdateReplyRequest
    ): ResponseEntity<ReplyResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(replyService.updateReply(feedId,replyId,updateReplyRequest))
    }

    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @PathVariable feedId: Long,
        @PathVariable replyId: Long,
        deleteReplyRequest: DeleteReplyRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(replyService.deleteReply(feedId,replyId,deleteReplyRequest))
    }
}