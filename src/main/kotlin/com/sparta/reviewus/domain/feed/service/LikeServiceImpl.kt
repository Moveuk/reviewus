package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.exception.UnauthorizedOperationException
import com.sparta.reviewus.domain.feed.model.Like
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.feed.repository.LikeRepository
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeServiceImpl(
    private val likeRepository: LikeRepository,
    private val feedRepository: FeedRepository
): LikeService {

    @Transactional
    override fun like(authenticatedMember: AuthenticatedMember, feedId: Long): Boolean {

        val feed = feedRepository.findByIdOrNull(feedId) ?: throw ModelNotFoundException("Feed", feedId)

        //좋아요 본인만 추가 할 수 있음
        if(feed.member.id == authenticatedMember.id) {
            throw UnauthorizedOperationException("자신의 글에는 좋아요를 누를 수 없습니다.")
        }

        //이미 좋아요를 눌렀었는지 확인하고, 있다면 삭제
        val confirmLike = likeRepository.existsByMemberIdAndFeedId(authenticatedMember.id, feedId)
        if (confirmLike) {
            likeRepository.deleteByMemberIdAndFeedId(authenticatedMember.id, feedId)
            return false
        }

        //좋아요 추가
        val addLike = Like(member = authenticatedMember.toMember(), feed = feed)

        likeRepository.save(addLike)

        return true


    }

    override fun countLikes(feedId: Long): Int { // 숫자
        return likeRepository.countByFeedId(feedId)
    }

    override fun isLiked(memberId: Long, feedId: Long): Boolean{
        return likeRepository.existsByMemberIdAndFeedId(memberId, feedId)
    }
}