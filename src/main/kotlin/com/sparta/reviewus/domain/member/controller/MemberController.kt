package com.sparta.reviewus.domain.member.controller

import com.sparta.reviewus.domain.member.dto.JoinRequest
import com.sparta.reviewus.domain.member.dto.LoginRequest
import com.sparta.reviewus.domain.member.dto.MemberResponse
import com.sparta.reviewus.domain.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.domain.member.service.MemberService
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
    fun getMemberProfile():ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.getMemberProfile())
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
        @RequestBody profileUpdateRequest: ProfileUpdateRequest
    ):ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.updateMemberProfile(profileUpdateRequest))
    }


}