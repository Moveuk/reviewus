package com.sparta.reviewus.domain.feed.service

import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.domain.feed.model.Like
import com.sparta.reviewus.domain.feed.repository.FeedRepository
import com.sparta.reviewus.domain.feed.repository.LikeRepository
import com.sparta.reviewus.domain.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeServiceImpl(
    private val likeRepository: LikeRepository,
    private val memberRepository: MemberRepository,
    private val feedRepository: FeedRepository
): LikeService {

    @Transactional // false -> true // true -> false
    override fun like(memberId: Long, feedId: Long): Boolean {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", memberId)
        val feed = feedRepository.findByIdOrNull(feedId) ?: throw ModelNotFoundException("Feed", feedId)

        //TODO 좋아요 본인만 추가 할 수 있게 구현


        //이미 좋아요를 눌렀었는지 확인하고 있다면 삭제
        val confirmLike = likeRepository.existsByMemberIdAndFeedId(memberId, feedId)
        if (confirmLike) {
            likeRepository.deleteByMemberIdAndFeedId(memberId, feedId)
            return false
        }

        //좋아요 추가
        val addLike = Like(member = member, feed = feed)
        likeRepository.save(addLike)
        return true


    }

    override fun countLikes(feedId: Long): Int { // 숫자
        return likeRepository.countByFeedId(feedId)
    }

    override fun isLiked(feedId: Long, memberId: Long): Boolean{
        return likeRepository.existsByMemberIdAndFeedId(memberId, feedId)
    }
}