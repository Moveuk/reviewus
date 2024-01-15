package com.sparta.reviewus.domain.exception

data class DuplicatedPropertyException(val property: String, val value: String) :
    RuntimeException("해당 $property $value 은 이미 존재하는 $property 입니다.")