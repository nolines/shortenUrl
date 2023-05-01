package com.urlShortenerApp.urlShortenerApp.service

import com.urlShortenerApp.urlShortenerApp.exception.HashNotFoundException
import com.urlShortenerApp.urlShortenerApp.model.Url
import com.urlShortenerApp.urlShortenerApp.repository.UrlRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class UrlShortenService(
    private val redis: RedisTemplate<String, String>,
    private val urlEncoder: UrlEncoder,
    private val repository: UrlRepository
) {
    fun shortenUrl(originalUrl: String): Url {
        val hash = urlEncoder.hash(originalUrl)

        val url = Url(originalUrl, hash)
        redis.opsForValue().set(hash, originalUrl)
        repository.save(url)

        return url
    }

    fun redirectUrl(hash: String): String? {
        // TODO : redis and postgres should have sync mechanism
        return redis.opsForValue().get(hash) ?: throw HashNotFoundException(hash)
    }

}
