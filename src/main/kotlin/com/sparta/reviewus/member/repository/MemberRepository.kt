package com.sparta.reviewus.member.repository

import com.sparta.reviewus.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>{
    fun existsMemberByEmail(email: String): Boolean
    fun findMemberByEmailAndPassword(email: String, password: String): Member?
}