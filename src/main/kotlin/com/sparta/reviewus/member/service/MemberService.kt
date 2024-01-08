package com.sparta.reviewus.member.service

import com.sparta.reviewus.member.dto.ProfileUpdateRequest

interface MemberService {

    fun getMemberProfile(profile: String)

    fun getOtherProfile(memberId: Long)

    fun updateMemberProfile(profile: String, profileUpdateRequest: ProfileUpdateRequest)

}