package com.urlShortenerApp.urlShortenerApp.repository

import com.urlShortenerApp.urlShortenerApp.model.Url
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UrlRepositoryTest(
    @Autowired
    private var urlRepository: UrlRepository
) {

    @Test
    fun `shouldReturnUrl`() {
        val originalUrl = "www.google.com"
        val shortenUrl = "dkb.com/191347bf"

        urlRepository.save(Url(originalUrl, shortenUrl))

        val result = urlRepository.findByShortenUrl(shortenUrl)

        Assertions.assertEquals(result.originalUrl, originalUrl)
        Assertions.assertEquals(result.shortenUrl, shortenUrl)
    }
}