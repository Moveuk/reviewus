package com.sparta.reviewus.domain.member.service

import com.sparta.reviewus.domain.exception.member.BadCredentialsException
import com.sparta.reviewus.domain.member.model.Member
import com.sparta.reviewus.domain.member.repository.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    val memberRepository: MemberRepository
) {
    fun authenticateUser(request: HttpServletRequest): Member {
        val authorization = request.getHeaders("Authorization")
        val encodedBasicAuth = authorization.nextElement() //Basic YWRtaW46MTIzNA==

        //Basic 떼기
        val encodedAuthInfo = encodedBasicAuth.split(" ")[1] //YWRtaW46MTIzNA==

        // Base64기반 Decode
        val decoder = Base64.getMimeDecoder()
        val decoded = String(decoder.decode(encodedAuthInfo)) //admin:1234

        //: 기준 email, password 분리
        val (email, password) = decoded.split(":")

        //이메일 비밀번호로 검색시 없으면 throw BadCredentialsException
        val authenticatedMember = memberRepository.findMemberByEmailAndPassword(email, password) ?: throw BadCredentialsException()

        //정상이면 인증된 유저 반환
        return authenticatedMember
    }

}