package com.sparta.reviewus.domain.member.service

import com.sparta.reviewus.domain.member.dto.*

interface MemberService {

    fun getMemberProfile(authenticatedMember: AuthenticatedMember): MemberResponse

    fun getOtherMember(memberId: Long): MemberResponse

    fun updateMemberProfile(authenticatedMember: AuthenticatedMember, profileUpdateRequest: ProfileUpdateRequest): MemberResponse

    fun join(joinRequest: JoinRequest): String

    fun login(loginRequest: LoginRequest): String

}