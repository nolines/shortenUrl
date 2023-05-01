package com.urlShortenerApp.urlShortenerApp.model

import jakarta.persistence.*
import lombok.Getter
import lombok.Setter


@Entity
@Getter
@Setter
@Table(name = "URLs")
class Url(
    var originalUrl: String,
    var shortenUrl: String
) {
    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

}