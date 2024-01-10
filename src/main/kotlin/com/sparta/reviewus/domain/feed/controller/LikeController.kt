package com.sparta.reviewus.domain.feed.controller

import com.sparta.reviewus.domain.feed.service.LikeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/feed/{feedId}/like")
@RestController
class LikeController(
    private val likeService: LikeService
) {

    @PostMapping
    fun like(
        @PathVariable feedId: Long,
        @RequestParam memberId: Long,
    ): ResponseEntity<String>{

        val isLiked = likeService.like(memberId, feedId)
        val message = if(isLiked) "좋아요를 눌렀습니다" else "좋아요가 취소되었습니다"

        return ResponseEntity.ok(message)
    }

    @GetMapping("/count")
    fun countLikes(@PathVariable feedId: Long): ResponseEntity<Int> {
        val likesCount = likeService.countLikes(feedId)
        return ResponseEntity.ok(likesCount)
    }
}