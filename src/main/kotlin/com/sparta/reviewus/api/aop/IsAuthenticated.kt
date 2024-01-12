package com.sparta.reviewus.api.aop


/**
 * 인증이 필요한 부분에 붙이는 AOP 어노테이션입니다.
 *
 * 컨트롤러 메서드에만 사용할 수 있습니다.
 *
 * [AuthAspect]에서 이 어노테이션을 검사합니다.
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class IsAuthenticated
