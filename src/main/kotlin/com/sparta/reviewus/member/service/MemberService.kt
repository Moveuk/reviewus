package com.sparta.reviewus.member.service

import com.sparta.reviewus.member.dto.JoinRequest
import com.sparta.reviewus.member.dto.MemberResponse
import com.sparta.reviewus.member.dto.ProfileUpdateRequest

interface MemberService {

    fun getMemberProfile(): MemberResponse

    fun getOtherProfile(memberId: Long): MemberResponse

    fun updateMemberProfile(profileUpdateRequest: ProfileUpdateRequest): MemberResponse

    fun join(joinRequest: JoinRequest): String

}