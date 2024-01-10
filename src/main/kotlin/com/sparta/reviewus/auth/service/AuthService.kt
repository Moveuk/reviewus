package com.sparta.reviewus.auth.service

import com.sparta.reviewus.domain.exception.member.BadCredentialsException
import com.sparta.reviewus.domain.member.dto.BasicAuthMember
import com.sparta.reviewus.domain.member.model.Member
import com.sparta.reviewus.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    val memberRepository: MemberRepository
) {
    fun authenticateMember(member: BasicAuthMember): Member {
        //이메일 비밀번호로 검색시 없으면 throw BadCredentialsException
        val authenticatedMember =
            memberRepository.findMemberByEmailAndPassword(member.email, member.password) ?: throw BadCredentialsException()

        //정상이면 인증된 유저 반환
        return authenticatedMember
    }

}