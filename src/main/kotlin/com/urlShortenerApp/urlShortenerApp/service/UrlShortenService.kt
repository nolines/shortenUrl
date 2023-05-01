package com.urlShortenerApp.urlShortenerApp.service

import com.urlShortenerApp.urlShortenerApp.model.Url
import org.springframework.stereotype.Service

@Service
class UrlShortenService {
    fun shortenUrl(originalUrl: String): Url {
        return Url("" ,"")
    }

    fun redirectUrl(hash: String): String? {
        return ""
    }

}
