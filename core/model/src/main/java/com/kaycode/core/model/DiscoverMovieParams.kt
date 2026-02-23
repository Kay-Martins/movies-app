package com.kaycode.core.model

data class DiscoverMovieParams(
    val includeAdult: Boolean = false,
    val includeVideo: Boolean = false,
    val language: String = "en-US",
    val primaryReleaseDateGte: String,
    val primaryReleaseDateLte: String,
    val sortBy: String = "primary_release_date.desc"
)
