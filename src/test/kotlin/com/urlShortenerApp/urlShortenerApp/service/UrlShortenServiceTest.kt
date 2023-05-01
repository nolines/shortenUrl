package com.urlShortenerApp.urlShortenerApp.service

import com.urlShortenerApp.urlShortenerApp.exception.HashNotFoundException
import com.urlShortenerApp.urlShortenerApp.model.Url
import com.urlShortenerApp.urlShortenerApp.repository.UrlRepository
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
    private lateinit var urlRepository: UrlRepository

    @BeforeEach
    fun init() {
        urlEncoder = mockk()
        redisTemplate = mockk()
        urlRepository = mockk()
        urlShortenService =
            UrlShortenService(redis = redisTemplate, urlEncoder = urlEncoder, repository = urlRepository)
    }

    @Test
    fun `shouldShortenUrl`() {

        val originalUrl = "www.google.com"
        val shortenUrl = "191347"
        val url = Url(originalUrl, shortenUrl)

        every { urlEncoder.hash(originalUrl) } returns shortenUrl
        every { redisTemplate.opsForValue().set(any(), any()) } answers {}
        every { urlRepository.save(any()) } returns url

        val result = urlShortenService.shortenUrl(originalUrl)

        verify { urlEncoder.hash(originalUrl) }
        verify { redisTemplate.opsForValue().set(any(), any()) }
        verify { urlRepository.save(any()) }

        Assertions.assertEquals(result.shortenUrl, shortenUrl)
    }

    @Test
    fun `shouldRedirectUrl`() {
        val originalUrl = "www.google.com"
        val shortenUrl = "dkb.com/191347bf"

        every { redisTemplate.opsForValue().get(shortenUrl) } returns originalUrl

        val result = urlShortenService.redirectUrl(shortenUrl)

        verify { redisTemplate.opsForValue().get(shortenUrl) }

        Assertions.assertEquals(result, originalUrl)
    }

    @Test
    fun `shouldThrowExceptionWhenThereIsNoUrl`() {
        val shortenUrl = "dkb.com/191347bf"

        every { redisTemplate.opsForValue().get(shortenUrl) } throws HashNotFoundException(shortenUrl)
        var exceptionThrown: Boolean = false

        try {
            urlShortenService.redirectUrl(shortenUrl)
        } catch (e: HashNotFoundException) {
            exceptionThrown = true
        }

        Assertions.assertTrue(exceptionThrown)
        verify { redisTemplate.opsForValue().get(shortenUrl) }
    }
}