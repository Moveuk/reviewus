package com.sparta.reviewus.domain.member.repository

import com.sparta.reviewus.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun existsMemberByEmail(email: String): Boolean
    fun findMemberByEmail(email: String): Member?
    fun findMemberByEmailAndPassword(email: String, password: String): Member?
}