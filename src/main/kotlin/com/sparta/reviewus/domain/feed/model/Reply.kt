package com.sparta.reviewus.domain.feed.model

import com.sparta.reviewus.domain.feed.dto.ReplyResponse
import com.sparta.reviewus.domain.member.model.Member
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "reply")
class Reply(

    @Column(name = "content")
    var content: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "create_date")
    val createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = " modified_date")
    var modifiedDate: LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    val feed: Feed
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun Reply.toResponse(): ReplyResponse {
    return ReplyResponse(
        id = id!!,
        content = content,
        password = password,
        createDate = createdDate,
        modifiedDate = modifiedDate
    )
}