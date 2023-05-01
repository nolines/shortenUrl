package com.urlShortenerApp.urlShortenerApp.service

import com.urlShortenerApp.urlShortenerApp.model.Url
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class UrlShortenService(
    private val redis: RedisTemplate<String, String>,
    private val urlEncoder: UrlEncoder
) {
    fun shortenUrl(originalUrl: String): Url {
        val hash = urlEncoder.hash(originalUrl)

        val response = Url(originalUrl, hash)
        redis.opsForValue().set(hash, originalUrl)

        return response
    }

    fun redirectUrl(hash: String): String? {
        return redis.opsForValue().get(hash)
    }

}
