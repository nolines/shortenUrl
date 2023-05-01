package com.urlShortenerApp.urlShortenerApp.exception

class HashNotFoundException(hash: String) : RuntimeException("$hash not found") {}