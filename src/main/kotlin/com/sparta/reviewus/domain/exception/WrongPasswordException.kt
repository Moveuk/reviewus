package com.sparta.reviewus.domain.exception

data class WrongPasswordException(override val message: String = "패스워드가 일치하지 않습니다." )
    : RuntimeException(message)
