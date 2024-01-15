package com.sparta.reviewus.api.mail

import com.sparta.reviewus.api.aop.IsAuthenticated
import com.sparta.reviewus.domain.member.service.MailService
import com.sparta.reviewus.common.SESClient
import com.sparta.reviewus.domain.member.dto.AuthenticatedMember
import io.swagger.v3.oas.annotations.Parameter
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class MailController(
    private val mailService: MailService,
    private val sesClient: SESClient
) {
    @IsAuthenticated
    @PostMapping("/requestCode")
    fun mailVerification(
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ): ResponseEntity<String> {
        return runBlocking {
            println("${authenticatedMember.email} 에게 메일 전송 시도 중")
            sesClient.sendVerificationCodeMailTo(authenticatedMember.email).let {
                mailService.saveVerificationCode(authenticatedMember.email, it)
                ResponseEntity
                    .status(HttpStatus.OK)
                    .body("이메일 인증 번호 전송 완료!")
            }
        }
    }

    @IsAuthenticated
    @PostMapping("/verification")
    fun mail(
        @RequestParam code: Int,
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ): ResponseEntity<String> {
        mailService.checkEmailVerification(authenticatedMember.email, code)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("이메일 인증 완료!")
    }

}