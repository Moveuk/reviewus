package com.sparta.reviewus.domain.feed.controller

import com.sparta.reviewus.domain.feed.service.LikeService
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/feeds/{feedId}/likes")
@RestController
class LikeController(
    private val likeService: LikeService
) {

    @PostMapping
    fun like(
        @PathVariable feedId: Long,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ): ResponseEntity<String>{

        val isLiked = likeService.like(authenticatedMember, feedId)
        val message = if(isLiked) "좋아요를 눌렀습니다" else "좋아요가 취소되었습니다"

        return ResponseEntity.ok(message)
    }

    @GetMapping
    fun countLikes(@PathVariable feedId: Long): ResponseEntity<Int> {
        val likesCount = likeService.countLikes(feedId)
        return ResponseEntity.ok(likesCount)
    }
}