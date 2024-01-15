package com.sparta.reviewus.domain.exception

class EmailAlreadyVerifiedException(email: String) :
    RuntimeException("이메일 $email 은 이미 인증된 이메일 입니다.")