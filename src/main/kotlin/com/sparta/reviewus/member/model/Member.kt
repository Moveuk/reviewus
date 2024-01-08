package com.sparta.reviewus.member.model

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