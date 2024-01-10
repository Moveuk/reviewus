package com.sparta.reviewus.domain.feed.model

import com.sparta.reviewus.domain.feed.dto.CreateFeedResponse
import com.sparta.reviewus.domain.member.model.Member
import com.sparta.reviewus.domain.member.model.toResponse
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "feed")
class Feed(

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @Column(name = "category")
    var category: String,

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
fun Feed.toResponse(): CreateFeedResponse {
    return CreateFeedResponse(
        id = id!!,
        title = title,
        description = description,
        member = member.toResponse(),
        category = category,
        feedPicUrl = feedPicUrls,
        longitude = longitude,
        latitude = latitude
    )
}
