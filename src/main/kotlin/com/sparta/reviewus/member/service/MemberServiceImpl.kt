package com.sparta.reviewus.member.service

import com.sparta.reviewus.exception.DuplicatedPropertyException
import com.sparta.reviewus.exception.ModelNotFoundException
import com.sparta.reviewus.member.dto.JoinRequest
import com.sparta.reviewus.member.dto.MemberResponse
import com.sparta.reviewus.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.member.model.Member
import com.sparta.reviewus.member.model.Profile
import com.sparta.reviewus.member.model.toResponse
import com.sparta.reviewus.member.repository.MemberRepository
import org.springframework.data.repository.findByIdOrNull
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

        memberRepository.save(Member(email = email, password = password, name = name, profile = Profile(nickname = nickname)))
        return "회원가입 성공"
    }

    override fun getMemberProfile(): MemberResponse {
        val member = memberRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("Member", 1L)

        return member.toResponse()

    }

    override fun getOtherMember(memberId: Long): MemberResponse {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw ModelNotFoundException("Member", memberId)

        return member.toResponse()
    }

    @Transactional
    override fun updateMemberProfile(profileUpdateRequest: ProfileUpdateRequest): MemberResponse {
        val member = memberRepository.findByIdOrNull(1L) ?: throw ModelNotFoundException("Member", 1L)

        return member.toResponse()
    }
}