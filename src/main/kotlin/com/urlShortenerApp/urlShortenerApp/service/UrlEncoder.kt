package com.urlShortenerApp.urlShortenerApp.service

import org.springframework.stereotype.Service
import java.math.BigInteger
import java.security.MessageDigest

@Service
class UrlEncoder {
    private val digest = MessageDigest.getInstance("SHA-256")

    fun hash(url: String, length: Int = 16): String {
        val bytes = digest.digest(url.toByteArray())
        val hash = String.format("dkb.com/" + "%32x", BigInteger(1, bytes))

        return hash.take(length)
    }
}