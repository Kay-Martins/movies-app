package com.kaycode.feature.latest_films.di

import com.kaycode.feature.latest_films.data.ILatestFilmsRepository
import com.kaycode.feature.latest_films.data.LatestFilmsRepository
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

}
