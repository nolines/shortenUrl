package com.urlShortenerApp.urlShortenerApp.controller

import com.urlShortenerApp.urlShortenerApp.model.ShortenRequest
import com.urlShortenerApp.urlShortenerApp.model.Url
import com.urlShortenerApp.urlShortenerApp.service.UrlShortenService
import io.mockk.*
import jakarta.servlet.http.HttpServletResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlShortenControllerTest {

    private lateinit var service: UrlShortenService
    private lateinit var controller: UrlShortenController
    private lateinit var response: HttpServletResponse

    @BeforeEach
    fun init() {
        service = mockk()
        response = mockk()
        controller = UrlShortenController(service)
    }

    @Test
    fun `shouldProcessRequestThroughShortenUrl`() {

        val originalUrl = "google.com"
        val shortenUrl = "abc123"
        val url = Url(originalUrl, shortenUrl)

        every { service.shortenUrl(originalUrl) } returns url

        controller.urlShorten(ShortenRequest(originalUrl))

        verify { service.shortenUrl(originalUrl) }
    }

    @Test
    fun `shouldProcessRequestThroughRedirectUrl`() {
        val hashInput = "abc123"

        every { response.sendRedirect(any()) } just runs
        every { service.redirectUrl(any()) } returns ""

        controller.urlRedirect(hashInput, response)

        verify { service.redirectUrl(hashInput) }
    }
}