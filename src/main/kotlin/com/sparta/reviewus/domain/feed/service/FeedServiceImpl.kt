package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.feed.dto.CreateFeedRequest
import com.sparta.reviewus.domain.feed.dto.CreateFeedResponse
import com.sparta.reviewus.domain.feed.model.Feed
import com.sparta.reviewus.domain.feed.model.toResponse
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class FeedServiceImpl(
    private val feedRepository: FeedRepository,
    private val memberRepository: MemberRepository
) : FeedService {
    override fun createFeed(request: CreateFeedRequest): CreateFeedResponse {
        val member = memberRepository.findByIdOrNull(request.memberId) ?: throw ModelNotFoundException("Member",request.memberId) //이거는 무슨일을 하는 코드
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

}