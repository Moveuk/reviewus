package com.sparta.reviewus.domain.exception

open class AuthorizationException(override val message: String) :
    RuntimeException(message)
