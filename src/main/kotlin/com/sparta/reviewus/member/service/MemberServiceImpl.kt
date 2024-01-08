package com.sparta.reviewus.member.service

import com.sparta.reviewus.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    override fun getMemberProfile(profile: String) {
        TODO("Not yet implemented")
    }

    override fun getOtherProfile(memberId: Long) {
        TODO("Not yet implemented")
    }

    override fun updateMemberProfile(profile: String, profileUpdateRequest: ProfileUpdateRequest) {
        TODO("Not yet implemented")
    }
}