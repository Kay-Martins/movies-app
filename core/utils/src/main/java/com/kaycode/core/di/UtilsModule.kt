package com.kaycode.core.di

import com.kaycode.core.utils.DateUtils
import com.kaycode.core.utils.IDateUtils
import com.kaycode.core.utils.IImageUtils
import com.kaycode.core.utils.ImageUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    @Singleton
    fun provideDateUtils(dateUtils: DateUtils): IDateUtils = dateUtils

    @Provides
    @Singleton
    fun provideImageUtils(imageUtils: ImageUtils): IImageUtils = imageUtils

}