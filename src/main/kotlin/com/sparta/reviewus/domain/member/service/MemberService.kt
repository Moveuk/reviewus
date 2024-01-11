package com.sparta.reviewus.domain.member.service

import com.sparta.reviewus.domain.member.dto.JoinRequest
import com.sparta.reviewus.domain.member.dto.MemberResponse
import com.sparta.reviewus.domain.member.dto.ProfileUpdateRequest

interface MemberService {

    fun getMemberProfile(): MemberResponse

    fun getOtherMember(memberId: Long): MemberResponse

    fun updateMemberProfile(profileUpdateRequest: ProfileUpdateRequest): MemberResponse

    fun join(joinRequest: JoinRequest): String

}