package com.urlShortenerApp.urlShortenerApp.controller

import com.urlShortenerApp.urlShortenerApp.model.ShortenRequest
import com.urlShortenerApp.urlShortenerApp.model.ShortenResponse
import com.urlShortenerApp.urlShortenerApp.model.Url
import com.urlShortenerApp.urlShortenerApp.service.UrlShortenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@Validated
class UrlShortenController(@Autowired private val service: UrlShortenService) {

    /**
     * to shorten url with using the original url
     * */
    @PostMapping("/shorten")
    fun urlShorten(@RequestBody request: ShortenRequest): ResponseEntity<ShortenResponse> {
        val response: Url = service.shortenUrl(request.originalUrl)

        return ResponseEntity.ok().body(ShortenResponse(response.shortenUrl))
    }

    /**
     * accepts hash (shortenUrl) to redirect called to the
     * original url, in scope of MVP I decided to use MOVED_PERMANENTLY
     * but on the clientside the `path` in the response should be used
     * another option might be to use httpservletresponse and sendRedirect
     * to the clientside
     * */
    @GetMapping("/{hash}")
    fun urlRedirect(@PathVariable hash: String): ResponseEntity<HttpStatus> {

        val redirectedUrl = service.redirectUrl(hash) as String

        return ResponseEntity
            .status(HttpStatus.MOVED_PERMANENTLY)
            .location(URI.create(redirectedUrl))
            .header(HttpHeaders.CONNECTION, "close")
            .build()
    }

}