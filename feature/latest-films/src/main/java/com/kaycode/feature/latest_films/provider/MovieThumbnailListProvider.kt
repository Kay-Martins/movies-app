package com.kaycode.feature.latest_films.provider

import com.kaycode.core.model.MovieResult
import com.kaycode.core.model.MovieThumbnail
import com.kaycode.core.network.retrofit.utils.NetworkConstants
import com.kaycode.core.utils.IDateUtils
import com.kaycode.core.utils.ImageUtils
import javax.inject.Inject

class MovieThumbnailListProvider @Inject constructor(
    private val dateUtils: IDateUtils,
    private val imageUtils: ImageUtils
): IMovieThumbnailListProvider {
    override fun provideMovieThumbnailList(results: List<MovieResult>): List<MovieThumbnail> {
        val movieThumbnails = mutableListOf<MovieThumbnail>()
        results.forEach {
            val thumbnail = MovieThumbnail (
                id = it.id,
                title = it.title,
                posterPath = imageUtils.buildFinalImageUrl(
                    baseUrl = NetworkConstants.IMAGE_BASE_URL,
                    imagePath = it.posterPath
                ),
                releaseYear = dateUtils.getReleaseYearFromDateString(it.releaseDate),
                voteAverage = it.voteAverage
            )
            movieThumbnails.add(thumbnail)
        }
        return movieThumbnails
    }
}
