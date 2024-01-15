package com.sparta.reviewus.common

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.ses.SesClient
import aws.sdk.kotlin.services.ses.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SESClient(
    @Value("\${aws.access-key}") private val accessKey: String,
    @Value("\${aws.secret-key}") private val secretKey: String,
    @Value("\${aws.sender-email}") private val senderEmail: String,
) {
    suspend fun sendVerificationCodeMailTo(recipient: String): Int {
        val sender = senderEmail
        val code = generateRandomCode()
        val subject = "ReviewUs 이메일 인증 확인"
        val bodyHTML = (
                "<html>" + "<head></head>" + "<body>" +
                        "<h3>" + "요청하신 인증 번호입니다." + "</h3>" +
                        "<h1>$code</h1>" +
                        "<h3>" + "감사합니다." + "</h3>" +
                        "</body>" + "</html>"
                )

        send(sender, recipient, subject, bodyHTML)
        return code
    }

    suspend fun send(
        sender: String?,
        recipient: String,
        subjectVal: String?,
        bodyHTML: String?
    ) {

        val destinationOb = Destination {
            toAddresses = listOf(recipient)
        }

        val contentOb = Content {
            data = bodyHTML
        }

        val subOb = Content {
            data = subjectVal
        }

        val bodyOb = Body {
            html = contentOb
        }

        val msgOb = Message {
            subject = subOb
            body = bodyOb
        }

        val emailRequest = SendEmailRequest {
            destination = destinationOb
            message = msgOb
            source = sender
        }

        SesClient {
            region = "ap-northeast-2"
            credentialsProvider = StaticCredentialsProvider {
                accessKeyId = accessKey
                secretAccessKey = secretKey
            }
        }.use { sesClient ->
            println("Amazon SES에 AWS SDK for Kotlin을 이용하여 메일 전송 시도중 ...")
            sesClient.sendEmail(emailRequest)
        }
    }

    companion object {
        fun generateRandomCode(): Int {
            return (Math.random() * (90000)).toInt() + 100000 // (int) Math.random() * (최댓값-최소값+1) + 최소값
        }
    }
}