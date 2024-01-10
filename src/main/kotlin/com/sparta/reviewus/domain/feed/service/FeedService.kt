package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.CreateFeedResponse

interface FeedService {
    fun createFeed(request: CreateFeedRequest): CreateFeedResponse

    fun getFeeds(): List<CreateFeedResponse>

    fun getFeedById(id: Long): CreateFeedResponse

//    fun updateFeed(title: String, request: CreateFeedRequest): CreateFeedResponse

    fun deleteFeed(id: Long)
}
