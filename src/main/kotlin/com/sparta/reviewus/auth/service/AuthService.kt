package com.sparta.reviewus.auth.service

import com.sparta.reviewus.auth.exception.BadCredentialsException
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import com.sparta.reviewus.domain.member.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    val memberRepository: MemberRepository
) {
    fun authenticateMember(email: String, password: String): AuthenticatedMember {
        //이메일 비밀번호로 검색시 없으면 throw BadCredentialsException
        val authenticatedMember =
            memberRepository.findMemberByEmailAndPassword(email, password) ?: throw BadCredentialsException()

        //정상이면 인증된 유저 반환
        return AuthenticatedMember(
            id = authenticatedMember.id!!,
            email = authenticatedMember.email,
            name =  authenticatedMember.name,
            password = authenticatedMember.password,
            profile = authenticatedMember.profile
        )
    }

    fun authenticateMemberByJWT(email: String): AuthenticatedMember {
        val authenticatedMember =
            memberRepository.findMemberByEmail(email) ?: throw BadCredentialsException()

        return AuthenticatedMember(
            id = authenticatedMember.id!!,
            email = authenticatedMember.email,
            name =  authenticatedMember.name,
            password = authenticatedMember.password,
            profile = authenticatedMember.profile
        )
    }

}