package com.sparta.reviewus.auth.exception

open class AuthenticationException(override val message: String) :
    RuntimeException(message)