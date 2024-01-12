package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.FeedByIdResponse
import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.feed.dto.UpdateFeedRequest

interface FeedService {
    fun createFeed(request: CreateFeedRequest): FeedResponse

    fun getFeeds(): List<FeedResponse>

    fun getFeedById(id: Long, memberId:Long?): FeedByIdResponse

    fun updateFeed(id: Long, request: UpdateFeedRequest): FeedResponse

    fun deleteFeed(id: Long)
}
