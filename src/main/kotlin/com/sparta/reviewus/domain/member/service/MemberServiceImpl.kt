package com.sparta.reviewus.domain.member.service

import com.sparta.reviewus.common.JWTUtil
import com.sparta.reviewus.domain.exception.DuplicatedPropertyException
import com.sparta.reviewus.domain.exception.ModelNotFoundException
import com.sparta.reviewus.auth.exception.BadCredentialsException
import com.sparta.reviewus.domain.member.dto.JoinRequest
import com.sparta.reviewus.domain.member.dto.LoginRequest
import com.sparta.reviewus.domain.member.dto.MemberResponse
import com.sparta.reviewus.domain.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.domain.member.model.Member
import com.sparta.reviewus.domain.member.model.Profile
import com.sparta.reviewus.domain.member.model.toResponse
import com.sparta.reviewus.domain.member.repository.MemberRepository
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
        if (memberRepository.existsMemberByEmail(email)) throw DuplicatedPropertyException(
            "이메일",
            email
        )

        memberRepository.save(Member(email = email, password = password, name = name, profile = Profile(nickname = nickname)))
        return "회원가입 성공"
    }

    override fun login(loginRequest: LoginRequest): String {
        val authenticatedMember =
            memberRepository.findMemberByEmailAndPassword(loginRequest.email, loginRequest.password) ?: throw BadCredentialsException()

        return JWTUtil.generateToken(authenticatedMember.email)
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

        val member = memberRepository.findByIdOrNull(profileUpdateRequest.id) ?: throw ModelNotFoundException("Member", profileUpdateRequest.id)

        val (id, name, profilePicUrl, nickname, password, introduction, address, interest) = profileUpdateRequest

        member.id = id
        member.name = name
        member.profile.profilePicUrl = profilePicUrl
        member.profile.nickname = nickname
        member.password = password
        member.profile.introduction = introduction
        member.profile.address = address
        member.profile.interest = interest

        return memberRepository.save(member).toResponse()
    }
}