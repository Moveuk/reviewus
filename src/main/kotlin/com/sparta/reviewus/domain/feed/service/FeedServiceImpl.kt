package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.AccessDeniedException
import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.FeedByIdResponse
import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.feed.dto.UpdateFeedRequest
import com.sparta.reviewus.domain.feed.model.Feed
import com.sparta.reviewus.domain.feed.model.toResponse
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class FeedServiceImpl(
    private val feedRepository: FeedRepository,
    private val likeService: LikeService
) : FeedService {

    override fun getFeeds(): List<FeedResponse> {
        return feedRepository.findAll().map { it.toResponse() }
    }

    override fun getFeedById(feedId: Long, authenticatedMember: AuthenticatedMember?): FeedByIdResponse {
        val feed = feedRepository.findByIdOrNull(feedId) ?: throw ModelNotFoundException("feed", feedId)

        val countLikes = likeService.countLikes(feedId)

        val isLiked = if (authenticatedMember != null) likeService.isLiked(authenticatedMember.id, feedId)
        else false

        return FeedByIdResponse(
            id = feedId!!,
            title = feed.title,
            description = feed.description,
            nickname = feed.member.profile.nickname,
            createDate = feed.createDate,
            category = feed.category,
            feedPicUrl = feed.feedPicUrls,
            longitude = feed.longitude,
            latitude = feed.latitude,
            likeCount = countLikes,
            likedByCurrentUser = isLiked,
            replies = feed.replies.map { it.toResponse() }
        )
    }

    @Transactional
    override fun createFeed(request: CreateFeedRequest, authenticatedMember: AuthenticatedMember): FeedResponse {
        return feedRepository.save(
            Feed(
                title = request.title,
                category = request.category,
                feedPicUrls = request.feedPicUrls,
                longitude = request.longitude,
                latitude = request.latitude,
                description = request.description,
                member = authenticatedMember.toMember()
            )
        ).toResponse()
    }

    @Transactional
    override fun updateFeed(feedId: Long, request: UpdateFeedRequest, authenticatedMember: AuthenticatedMember): FeedResponse {
        val feed = feedRepository.findByIdOrNull(feedId) ?: throw ModelNotFoundException("feed", feedId)

        if (feed.member.id != authenticatedMember.id) throw AccessDeniedException()

        feed.title = request.title
        feed.description = request.description
        feed.feedPicUrls = request.feedPicUrls
        feed.longitude = request.longitude
        feed.latitude = request.latitude
        feed.category = request.category

        return feedRepository.save(feed).toResponse()
    }

    @Transactional
    override fun deleteFeed(feedId: Long, authenticatedMember: AuthenticatedMember) {
        val feed = feedRepository.findByIdOrNull(feedId) ?: throw ModelNotFoundException("feed", feedId)

        if (feed.member.id != authenticatedMember.id) throw AccessDeniedException()

        feedRepository.delete(feed)
    }
}

