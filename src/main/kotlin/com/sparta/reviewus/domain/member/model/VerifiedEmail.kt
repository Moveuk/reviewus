package com.sparta.reviewus.domain.member.model

import jakarta.persistence.*

@Entity
@Table(name = "verified_email")
class VerifiedEmail(
    @Column(name="email", nullable = false)
    val email: String,

    @Column(name="code", nullable = false)
    var code: Int,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name="is_verified", nullable = false)
    var isVerified: Boolean = false
}