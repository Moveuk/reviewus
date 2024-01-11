package com.sparta.reviewus.common

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object JWTUtil {
    private const val ONE_DAY = 1000L * 60L * 60L * 24L
    const val EXPIRATION_TIME = ONE_DAY
    private const val SECRET_KEY = "sparta-reviewus-auth-secret-key-example"
    private val signingKey = Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))
        ?: throw IllegalStateException("Token을 발급하기 위한 Key가 적절하게 생성되지 않음")

    fun generateToken(
        userId: String,
        expirationInMillisecond: Long = EXPIRATION_TIME
    ): String {
        val now = Date()
        val expiration = Date(now.time + expirationInMillisecond)
        val claims = generateClaims(now, expiration)
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userId)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(signingKey, SignatureAlgorithm.HS256)
            .compact()
    }

    /**
     * LocalDateTime을 직렬화 하기 위해서는 JavaTimeModule이 등록되어 있어야 합니다.
     * SerializationFeature.WRITE_DATES_AS_TIMESTAMPS 설정을 통해 직렬화를 보기 쉽게 만듭니다.
     * */
    private fun generateClaims(now: Date, expiration: Date): Map<String, String> {
        val nowLocalDateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault())
        val expirationLocalDateTime = LocalDateTime.ofInstant(expiration.toInstant(), ZoneId.systemDefault())

        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)

        return mapOf(
            "issuedAt" to mapper.writeValueAsString(nowLocalDateTime),
            "expiredAt" to mapper.writeValueAsString(expirationLocalDateTime),
        )
    }

    fun getUserIdFromToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun isValidToken(token: String): Boolean {
        val body = Jwts.parserBuilder()
            .setSigningKey(signingKey)
            .build()
            .parseClaimsJws(token)
            .body

        println(body.expiration.toString())

        val expirationLocalDateTime = LocalDateTime.ofInstant(body.expiration.toInstant(), ZoneId.systemDefault())
        val nowLocalDateTime = LocalDateTime.now()

        return !nowLocalDateTime.isAfter(expirationLocalDateTime)
    }

    fun getTokenFromAuthorizationHeader(headerValue: String): String {
        return headerValue.split(" ")[1]
    }
}