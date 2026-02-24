package com.kaycode.feature.latest_films.provider

import com.kaycode.core.model.MovieThumbnail
import com.kaycode.core.model.MovieResult

interface IMovieThumbnailListProvider {
    fun provideMovieThumbnailList(results: List<MovieResult>): List<MovieThumbnail>
}