package com.urlShortenerApp.urlShortenerApp.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.data.redis.core.RedisTemplate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlShortenServiceTest {

    private lateinit var urlEncoder: UrlEncoder
    private lateinit var redisTemplate: RedisTemplate<String, String>
    private lateinit var urlShortenService: UrlShortenService

    @BeforeEach
    fun init() {
        urlEncoder = mockk()
        redisTemplate = mockk()
        urlShortenService =
            UrlShortenService(redis = redisTemplate, urlEncoder = urlEncoder)
    }

    @Test
    fun `shouldShortenUrl`() {

        val originalUrl = "www.google.com"
        val shortenUrl = "191347"

        every { urlEncoder.hash(originalUrl) } returns shortenUrl
        every { redisTemplate.opsForValue().set(any(), any()) } answers {}

        val result = urlShortenService.shortenUrl(originalUrl)

        verify { urlEncoder.hash(originalUrl) }
        verify { redisTemplate.opsForValue().set(any(), any()) }

        Assertions.assertEquals(result.shortenUrl, shortenUrl)
    }

    @Test
    fun `shouldRedirectUrl`() {
        val originalUrl = "www.google.com"
        val shortenUrl = "123321123"

        every { redisTemplate.opsForValue().get(shortenUrl) } returns originalUrl

        val result = urlShortenService.redirectUrl(shortenUrl)

        verify { redisTemplate.opsForValue().get(shortenUrl) }

        Assertions.assertEquals(result, originalUrl)
    }
}