package com.sparta.reviewus.member.service

import com.sparta.reviewus.member.dto.MemberResponse
import com.sparta.reviewus.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {

    override fun getMemberProfile(): MemberResponse {
        TODO()
    }

    override fun getOtherProfile(memberId: Long): MemberResponse {
        TODO()
    }

    @Transactional
    override fun updateMemberProfile(profileUpdateRequest: ProfileUpdateRequest): MemberResponse {
        TODO()
    }
}