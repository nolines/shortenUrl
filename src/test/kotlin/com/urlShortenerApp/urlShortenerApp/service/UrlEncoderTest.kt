package com.urlShortenerApp.urlShortenerApp.service

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UrlEncoderTest {

    private lateinit var urlEncoder: UrlEncoder

    @BeforeEach
    fun init() {
        urlEncoder = UrlEncoder()
    }

    @Test
    fun `shouldEncodeUrl`() {
        val originalUrl = "www.google.com"
        val expectedUrl = "dkb.com/191347bf"

        var result = urlEncoder.hash(originalUrl)

        Assertions.assertEquals(result, expectedUrl)
    }
}