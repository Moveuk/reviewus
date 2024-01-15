package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.FeedByIdResponse
import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.feed.dto.UpdateFeedRequest
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember

interface FeedService {
    fun getFeeds(): List<FeedResponse>

    fun getFeedById(feedId: Long, authenticatedMember: AuthenticatedMember?): FeedByIdResponse

    fun createFeed(request: CreateFeedRequest, authenticatedMember: AuthenticatedMember): FeedResponse

    fun updateFeed(feedId: Long, request: UpdateFeedRequest, authenticatedMember: AuthenticatedMember): FeedResponse

    fun deleteFeed(feedId: Long, authenticatedMember: AuthenticatedMember)
}
