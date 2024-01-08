package com.sparta.reviewus.member.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime

@Embeddable
class Profile (
    @Column(name="nickname", nullable = false)
    var Nicname: String,

    @Column(name="profile_pic_url")
    var profilePicUrl: String,

    @Column(name="introduction")
    var Introduction: String,

    @Column(name="address")
    var Address: String,

    @Column(name="interest")
    var Interest: String,

    @Column(name="create_date")
    val CreateDate: LocalDateTime = LocalDateTime.now(),

    @Column(name="modified_date")
    val ModifiedDate: LocalDateTime = LocalDateTime.now()
)