package com.kaycode.core.di

import com.kaycode.core.utils.DateUtils
import com.kaycode.core.utils.IDateUtils
import com.kaycode.core.utils.IImageUtils
import com.kaycode.core.utils.ImageUtils
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    @Singleton
    abstract fun bindDateUtils(dateUtils: DateUtils): IDateUtils

    @Binds
    @Singleton
    abstract fun bindImageUtils(imageUtils: ImageUtils): IImageUtils

}