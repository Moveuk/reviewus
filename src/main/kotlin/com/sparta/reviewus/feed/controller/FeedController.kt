package com.sparta.reviewus.feed.controller

import com.sparta.reviewus.feed.dto.CreateFeedRequest
import com.sparta.reviewus.feed.dto.CreateFeedResponse
import com.sparta.reviewus.feed.dto.UpdateFeedRequest
import com.sparta.reviewus.feed.service.FeedService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/feeds")
@RestController
class FeedController(
    private val feedService: FeedService
) {


    @GetMapping
    fun getFeedList() {
        TODO()
    }

    @GetMapping("/{feedId}")
    fun getFeed(@PathVariable feedId: Long) {
        TODO()
    }

    @PostMapping
    fun createFeed(@RequestBody createFeedRequest: CreateFeedRequest): ResponseEntity<CreateFeedResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(feedService.createFeed(createFeedRequest))
    }

    @PutMapping("/{feedId}")
    fun updateFeed(@PathVariable feedId: Long, @RequestBody updateFeedRequest: UpdateFeedRequest) {
        TODO()
    }

    @DeleteMapping("/{feedId}")
    fun deleteFeed(@PathVariable feedId: Long) {
        TODO()
    }


}