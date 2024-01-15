package com.sparta.reviewus.domain.member.controller

import com.sparta.reviewus.domain.member.dto.*
import com.sparta.reviewus.domain.member.service.MemberService
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/join")
    fun join(@Valid @RequestBody joinRequest: JoinRequest):ResponseEntity<String>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(memberService.join(joinRequest))
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest):ResponseEntity<String>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.login(loginRequest))
    }

    @GetMapping("/profile")
    fun getMemberProfile(
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember
    ):ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.getMemberProfile(authenticatedMember))
    }

    @GetMapping("/members/{memberId}")
    fun getOtherMember(
        @PathVariable memberId: Long
    ):ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.getOtherMember(memberId))
    }

    @PutMapping("/profile")
    fun updateMemberProfile(
        @Parameter(hidden = true) authenticatedMember: AuthenticatedMember,
        @RequestBody @Valid profileUpdateRequest: ProfileUpdateRequest
    ):ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.updateMemberProfile(authenticatedMember, profileUpdateRequest))
    }


}