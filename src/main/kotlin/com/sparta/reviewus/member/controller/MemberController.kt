package com.sparta.reviewus.member.controller

import com.sparta.reviewus.member.dto.JoinRequest
import com.sparta.reviewus.member.dto.MemberResponse
import com.sparta.reviewus.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.member.service.MemberService
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