package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.FeedByIdResponse
import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.feed.dto.UpdateFeedRequest
import com.sparta.reviewus.domain.feed.model.Feed
import com.sparta.reviewus.domain.feed.model.toResponse
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.member.model.toResponse
import com.sparta.reviewus.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class FeedServiceImpl(
    private val feedRepository: FeedRepository,
    private val memberRepository: MemberRepository,
    private val likeService: LikeService
) : FeedService {
    @Transactional
    override fun createFeed(request: CreateFeedRequest): FeedResponse {
        val member = memberRepository.findByIdOrNull(request.memberId) ?: throw ModelNotFoundException(
            "Member",
            request.memberId
        )
        return feedRepository.save(
            Feed(
                title = request.title,
                category = request.category,
                feedPicUrls = request.feedPicUrl,
                longitude = request.longitude,
                latitude = request.latitude,
                description = request.description,
                member = member
            )
        ).toResponse()
    }

    override fun getFeeds(): List<FeedResponse> {
        return feedRepository.findAll().map { it.toResponse() }
    }

    override fun getFeedById(id: Long, memberId:Long?): FeedByIdResponse {
        val feed = feedRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("feed", id)

        val countLikes = likeService.countLikes(id)


        val isLiked = if (memberId != null) likeService.isLiked(memberId, id)
        else false

        return FeedByIdResponse(
            id = id!!,
            title = feed.title,
            description = feed.description,
            member = feed.member.toResponse(),
            category = feed.category,
            feedPicUrl = feed.feedPicUrls,
            longitude = feed.longitude,
            latitude = feed.latitude,
            likeCount = countLikes,
            likedByCurrentUser = isLiked
        )
    }

    @Transactional
    override fun deleteFeed(id: Long) {
        val feed = feedRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("feed", id)
        feedRepository.delete(feed)
    }

    override fun updateFeed(id: Long, request: UpdateFeedRequest): FeedResponse {
        val feed = feedRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("feed", id)

        feed.title = request.title
        feed.description = request.description
        return feedRepository.save(feed).toResponse()
    }
}

