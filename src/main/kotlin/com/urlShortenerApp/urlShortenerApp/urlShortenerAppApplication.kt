package com.urlShortenerApp.urlShortenerApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
class UrlShortenerApp

fun main(args: Array<String>) {
    runApplication<UrlShortenerApp>(*args)
}
