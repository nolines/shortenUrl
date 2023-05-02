package com.urlShortenerApp.urlShortenerApp.model

import lombok.AllArgsConstructor
import lombok.Generated
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "urls")
data class Url(
    var originalUrl: String,
    var shortenUrl: String
) {
    @Id
    @Generated
    lateinit var id: String
}