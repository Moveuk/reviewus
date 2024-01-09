package com.sparta.reviewus.member.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
class Profile (
    @Column(name="nickname", nullable = false)
    var nickname: String,

    @Column(name="profile_pic_url")
    var profilePicUrl: String = "https://imgur.com/S8jQ6wN",

    @Column(name="introduction")
    var introduction: String = "$nickname 님의 자기소개입니다.",

    @Column(name="address")
    var address: String? = null,

    @Column(name="interest")
    var interest: String? = null,

    @Column(name="create_date")
    val createDate: LocalDateTime = LocalDateTime.now(),

    @Column(name="modified_date")
    var modifiedDate: LocalDateTime = LocalDateTime.now()
)