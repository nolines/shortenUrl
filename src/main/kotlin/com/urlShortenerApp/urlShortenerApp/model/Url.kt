package com.urlShortenerApp.urlShortenerApp.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import lombok.Getter
import lombok.Setter

@Entity
@Getter
@Setter
class Url(
    var originalUrl: String,
    var shortenUrl: String
) {
    @Id
    @GeneratedValue
    private var id: Long = 0

}