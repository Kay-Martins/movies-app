package com.kaycode.feature.latest_films.usecase

import com.kaycode.core.model.DiscoverMovieResponse
import com.kaycode.core.network.retrofit.model.ApiResponse
import com.kaycode.feature.latest_films.data.ILatestFilmsRepository
import javax.inject.Inject

class GetLatestFilmsUseCase @Inject constructor(
    private val latestFilmsRepository: ILatestFilmsRepository
): IGetLatestFilmsUseCase {
    override suspend operator fun invoke(page: Int): ApiResponse<DiscoverMovieResponse> =
        latestFilmsRepository.getLatestFilms(page)
}