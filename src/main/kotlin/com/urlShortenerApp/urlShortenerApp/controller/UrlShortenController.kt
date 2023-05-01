package com.urlShortenerApp.urlShortenerApp.controller

import com.urlShortenerApp.urlShortenerApp.model.ShortenRequest
import com.urlShortenerApp.urlShortenerApp.model.ShortenResponse
import com.urlShortenerApp.urlShortenerApp.model.Url
import com.urlShortenerApp.urlShortenerApp.service.UrlShortenService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UrlShortenController(@Autowired private val service: UrlShortenService) {

    @PostMapping("/shorten")
    fun urlShorten(@RequestBody request: ShortenRequest): ShortenResponse {
        val response: Url = service.shortenUrl(request.originalUrl)

        return ShortenResponse(response.shortenUrl)
    }

    @GetMapping("/{hash}")
    fun urlRedirect(@PathVariable hash: String, response: HttpServletResponse) {

        response.sendRedirect(service.redirectUrl(hash));
    }

}