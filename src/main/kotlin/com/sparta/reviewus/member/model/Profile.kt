package com.sparta.reviewus.member.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="member")
class Profile(

    @Column(name="email")
    val email: String,

    @Column(name="name")
    var name: String,

    @Column(name="profile_pic_url")
    var profilePicUrl: String,

    @Column(name="nickname")
    var Nicname: String,

    @Column(name="password")
    var password: String,

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

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}