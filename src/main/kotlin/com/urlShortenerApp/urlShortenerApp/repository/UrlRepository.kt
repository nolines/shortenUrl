package com.urlShortenerApp.urlShortenerApp.repository

import com.urlShortenerApp.urlShortenerApp.model.Url
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : MongoRepository<Url, Long> {

    fun findByShortenUrl(shortenUrl: String): Url
}