package com.sparta.reviewus.domain.feed.repository

import com.sparta.reviewus.domain.feed.model.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository : JpaRepository<Feed, Long> {

}
