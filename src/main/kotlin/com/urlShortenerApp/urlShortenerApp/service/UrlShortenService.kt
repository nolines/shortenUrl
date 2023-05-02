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

    /**
     * To shorten url with using the original URL
     * Its open to improvements such as using the redis to catch the data only for
     * 1 week and persist the shortUrl and originalUrl in the mongo to have some kind
     * of statistic calculation.
     * */
    fun shortenUrl(originalUrl: String): Url {
        val hash = urlEncoder.hash(originalUrl)

        val url = Url(originalUrl, hash)

        // TODO: will implement expiration date
        redis.opsForValue().set(hash, originalUrl)

        // TODO : relations for how many times clicked on the link, mostly statistic purposes
        repository.save(url)

        return url
    }

    /**
     * Redirects url by using the hash
     * */
    fun redirectUrl(hash: String): Any {
        // TODO : redis and postgres should have sync mechanism
        require(hash.isNotBlank()) { "Hash cannot be blank" }

        return (redis.opsForValue().get(hash)
            ?.takeIf { it.isNotBlank() }
            ?: repository.findByShortenUrl(hash)?.let { url ->
                redis.opsForValue().set(hash, url.originalUrl)
                url
            } ?: throw HashNotFoundException(hash))
    }
}