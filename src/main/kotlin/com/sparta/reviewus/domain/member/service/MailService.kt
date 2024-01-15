package com.sparta.reviewus.domain.member.service

import com.sparta.reviewus.domain.exception.EmailVerificationFailedException
import com.sparta.reviewus.domain.exception.EmailAlreadyVerifiedException
import com.sparta.reviewus.domain.member.model.VerifiedEmail
import com.sparta.reviewus.domain.member.repository.VerifiedEmailRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MailService(
    private val verifiedEmailRepository: VerifiedEmailRepository
) {
    @Transactional
    fun saveVerificationCode(email: String, code: Int) {
        val findVerifiedEmail = checkValidation(email)
        findVerifiedEmail.code = code
    }

    @Transactional
    fun checkEmailVerification(email: String, code: Int) {
        val findVerifiedEmail = checkValidation(email)
        if (findVerifiedEmail.code != code) throw EmailVerificationFailedException("알맞은 인증 코드를 입력해주세요.")
        findVerifiedEmail.isVerified = true
    }

    private fun checkValidation(email: String): VerifiedEmail {
        val findVerifiedEmail = verifiedEmailRepository.findVerifiedEmailByEmail(email) ?: throw EmailVerificationFailedException("먼저 회원가입을 해주세요!")
        if (findVerifiedEmail.isVerified) throw EmailAlreadyVerifiedException(email)
        return findVerifiedEmail
    }
}