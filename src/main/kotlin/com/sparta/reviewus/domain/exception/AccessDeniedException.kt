package com.sparta.reviewus.domain.exception

data class AccessDeniedException(override val message: String = "글에 대한 권한이 없습니다.")
    : AuthorizationException(message)
