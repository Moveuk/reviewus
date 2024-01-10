package com.sparta.reviewus.feed.repository

import com.sparta.reviewus.feed.model.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository : JpaRepository<Feed, Long> {

}
