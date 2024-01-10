package com.sparta.reviewus.feed.service

import com.sparta.reviewus.feed.dto.CreateFeedRequest
import com.sparta.reviewus.feed.dto.CreateFeedResponse

interface FeedService {
    fun createFeed(request: CreateFeedRequest): CreateFeedResponse

//    fun getFeed(request: CreateFeedRequest): FeedResponse
//
//    fun getFeedList(): List<FeedResponse>
//
//    fun getFeedById(Id: Long): FeedResponse
//
//    fun updateFeed(title: String, request: CreateFeedRequest): FeedResponse
//
//    fun deleteFeed(title: String)
}
