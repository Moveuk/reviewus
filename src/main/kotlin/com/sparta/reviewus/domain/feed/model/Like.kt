package com.sparta.reviewus.domain.feed.model

import com.sparta.reviewus.domain.member.model.Member
import jakarta.persistence.*

@Entity
@Table(name = "likes")
data class Like(

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @ManyToOne
    @JoinColumn(name = "feed_id", nullable = false)
    var feed: Feed

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
