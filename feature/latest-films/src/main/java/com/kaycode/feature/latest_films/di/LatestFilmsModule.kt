package com.kaycode.feature.latest_films.di

import com.kaycode.feature.latest_films.data.ILatestFilmsRepository
import com.kaycode.feature.latest_films.data.LatestFilmsRepository
import com.kaycode.feature.latest_films.manager.ILatestFilmsManager
import com.kaycode.feature.latest_films.manager.LatestFilmsManager
import com.kaycode.feature.latest_films.provider.IMovieThumbnailListProvider
import com.kaycode.feature.latest_films.provider.MovieThumbnailListProvider
import com.kaycode.feature.latest_films.usecase.GetLatestFilmsUseCase
import com.kaycode.feature.latest_films.usecase.IGetLatestFilmsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
class LatestFilmsModule {

    @Binds
    @ViewModelScoped
    fun bindLatestFilmsRepository(latestFilmsRepository: LatestFilmsRepository): ILatestFilmsRepository = latestFilmsRepository

    @Binds
    @ViewModelScoped
    fun bindGetLatestFilmsUseCase(getLatestFilmsUseCase: GetLatestFilmsUseCase): IGetLatestFilmsUseCase = getLatestFilmsUseCase

    @Binds
    @ViewModelScoped
    fun bindProvideMovieThumbnailList(movieThumbnailListProvider: MovieThumbnailListProvider): IMovieThumbnailListProvider = movieThumbnailListProvider

    @Binds
    @ViewModelScoped
    fun bindLatestFilmsManager(latestFilmsManager: LatestFilmsManager): ILatestFilmsManager = latestFilmsManager
}
