package com.sparta.reviewus.domain.feed.controller

import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.feed.dto.UpdateFeedRequest
import com.sparta.reviewus.domain.feed.service.FeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/feeds")
@RestController
class FeedController(
    private val feedService: FeedService
) {


    @GetMapping
    fun getFeedList() : ResponseEntity<List<FeedResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(feedService.getFeeds())
    }

    @GetMapping("/{feedId}")
    fun getFeed(@PathVariable feedId: Long): ResponseEntity<FeedResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(feedService.getFeedById(feedId))
    }

    @PostMapping
    fun createFeed(@RequestBody createFeedRequest: CreateFeedRequest): ResponseEntity<FeedResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(feedService.createFeed(createFeedRequest))
    }

    @PutMapping("/{feedId}")
    fun updateFeed(@PathVariable feedId: Long,
                   @RequestBody updateFeedRequest: UpdateFeedRequest) :ResponseEntity<FeedResponse>  {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(feedService.updateFeed(feedId, updateFeedRequest))
    }

    @DeleteMapping("/delete/{feedId}")
    fun deleteFeed(@PathVariable feedId: Long):ResponseEntity<Unit> {
        feedService.deleteFeed(feedId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}