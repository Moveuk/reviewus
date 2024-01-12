package com.sparta.reviewus.domain.feed.model

import com.sparta.reviewus.domain.feed.dto.FeedResponse
import com.sparta.reviewus.domain.member.model.Member
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "feed")
class Feed(

    @OneToMany(mappedBy = "feed", cascade = [CascadeType.ALL])
    var replies: MutableList<Reply> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    var category: Category,

    @Column(name = "title")
    var title : String,

    @Column(name = "feed_pic_urls")
    var feedPicUrls: String = "https://imgur.com/a/tBAKHUn",

    @Column(name = "longitude")
    var longitude : String?,

    @Column(name = "latitude")
    var latitude : String?,

    @Column(name =  "description")
    var description : String?,

    @Column(name = "create_date")
    var createDate : LocalDateTime = LocalDateTime.now(),

    @Column(name = "modified_date")
    var modifiedDate : LocalDateTime = LocalDateTime.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun Feed.toResponse(): FeedResponse {
    return FeedResponse(
        id = id!!,
        title = title,
        description = description,
        nickname = member.profile.nickname,
        category = category,
        feedPicUrl = feedPicUrls,
        longitude = longitude,
        latitude = latitude,
    )
}
