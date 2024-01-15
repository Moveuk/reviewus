package com.sparta.reviewus.domain.member.repository

import com.sparta.reviewus.domain.member.model.VerifiedEmail
import org.springframework.data.jpa.repository.JpaRepository

interface VerifiedEmailRepository: JpaRepository<VerifiedEmail, Long> {
    fun findVerifiedEmailByEmail(email: String): VerifiedEmail?
}