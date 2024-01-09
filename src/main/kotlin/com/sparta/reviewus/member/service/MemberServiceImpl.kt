package com.sparta.reviewus.member.service

import com.sparta.reviewus.exception.DuplicatedPropertyException
import com.sparta.reviewus.member.dto.JoinRequest
import com.sparta.reviewus.member.dto.MemberResponse
import com.sparta.reviewus.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.member.model.Member
import com.sparta.reviewus.member.model.Profile
import com.sparta.reviewus.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {

    override fun join(joinRequest: JoinRequest): String {
        val (email, password, name, nickname) = joinRequest

        //이메일 중복 체크
        if (memberRepository.existsUserByEmail(email)) throw DuplicatedPropertyException("이메일", email)

        memberRepository.save(Member(email = email, password = password, name = name, profile = Profile(nicname = nickname)))
        return "회원가입 성공"
    }

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