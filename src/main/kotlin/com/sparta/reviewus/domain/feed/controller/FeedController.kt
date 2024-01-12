package com.sparta.reviewus.domain.feed.controller

import com.sparta.reviewus.api.aop.IsAuthenticated
import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.FeedByIdResponse
import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.feed.dto.UpdateFeedRequest
import com.sparta.reviewus.domain.feed.service.FeedService
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/feeds")
@RestController
class FeedController(
    private val feedService: FeedService
) {
    @GetMapping
    fun getFeeds() : ResponseEntity<List<FeedResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(feedService.getFeeds())
    }

    @GetMapping("/{feedId}")
    fun getFeed(@PathVariable feedId: Long, @Parameter(hidden = true) authenticatedMember: AuthenticatedMember?): ResponseEntity<FeedByIdResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(feedService.getFeedById(feedId, authenticatedMember))
    }

    @IsAuthenticated
    @PostMapping
    fun createFeed(
        @RequestBody createFeedRequest: CreateFeedRequest,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ): ResponseEntity<FeedResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(feedService.createFeed(createFeedRequest, authenticatedMember))
    }

    @IsAuthenticated
    @PutMapping("/{feedId}")
    fun updateFeed(
        @PathVariable feedId: Long,
        @RequestBody updateFeedRequest: UpdateFeedRequest,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember) :ResponseEntity<FeedResponse>  {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(feedService.updateFeed(feedId, updateFeedRequest, authenticatedMember))
    }

    @IsAuthenticated
    @DeleteMapping("/{feedId}")
    fun deleteFeed(
        @PathVariable feedId: Long,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ):ResponseEntity<Unit> {
        feedService.deleteFeed(feedId, authenticatedMember)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}