package com.sparta.reviewus.member.repository

import com.sparta.reviewus.member.model.Member
import com.sparta.reviewus.member.model.Profile
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>{
    fun existsUserByEmail(email: String): Boolean
}