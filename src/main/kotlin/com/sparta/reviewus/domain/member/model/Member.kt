package com.sparta.reviewus.domain.member.model

import com.sparta.reviewus.domain.member.dto.MemberResponse
import jakarta.persistence.*

@Entity
@Table(name="member")
class Member(

    @Column(name="email", nullable = false)
    val email: String,

    @Column(name="name", nullable = false)
    var name: String,

    @Column(name="password", nullable = false)
    var password: String,

    @Embedded
    var profile: Profile

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Member.toResponse(): MemberResponse {
    return MemberResponse(
        id = id!!,
        email = email,
        name = name,
        nickname = profile.nickname,
        profilePicUrl = profile.profilePicUrl,
        introduction = profile.introduction,
        address = profile.address,
        interest = profile.interest
    )
}