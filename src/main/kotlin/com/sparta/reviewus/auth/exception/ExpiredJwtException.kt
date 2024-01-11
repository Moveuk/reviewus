package com.sparta.reviewus.auth.exception

import io.jsonwebtoken.JwtException

data class ExpiredJwtException(override val message: String?) : JwtException(message) {
}