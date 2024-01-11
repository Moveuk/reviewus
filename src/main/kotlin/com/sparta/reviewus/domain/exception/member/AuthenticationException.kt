package com.sparta.reviewus.domain.exception.member

open class AuthenticationException(override val message: String) :
    RuntimeException(message)