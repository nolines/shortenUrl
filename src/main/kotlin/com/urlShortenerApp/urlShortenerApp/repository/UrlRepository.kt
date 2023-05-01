package com.urlShortenerApp.urlShortenerApp.repository

import com.urlShortenerApp.urlShortenerApp.model.Url
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : JpaRepository<Url, Long> {

    fun findByShortenUrl(shortenUrl: String): Url
}