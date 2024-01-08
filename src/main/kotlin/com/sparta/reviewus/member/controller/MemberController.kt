package com.sparta.reviewus.member.controller

import com.sparta.reviewus.member.dto.MemberResponse
import com.sparta.reviewus.member.dto.ProfileUpdateRequest
import com.sparta.reviewus.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/profile")
    fun getMemberProfile():ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.getMemberProfile())
    }

    @GetMapping("/members/{memberId}")
    fun getOtherProfile(
        @PathVariable memberId: Long
    ):ResponseEntity<MemberResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(memberService.getOtherProfile(memberId))
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