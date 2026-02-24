package com.kaycode.feature.latest_films.usecase

import com.kaycode.core.model.DiscoverMovieResponse
import com.kaycode.core.network.retrofit.model.ApiResponse

interface IGetLatestFilmsUseCase {
    suspend operator fun invoke(page: Int): ApiResponse<DiscoverMovieResponse>
}