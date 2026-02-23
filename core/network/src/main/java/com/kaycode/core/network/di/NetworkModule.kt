package com.kaycode.core.network.di

import com.kaycode.core.network.handler.ApiHandler
import com.kaycode.core.network.handler.IApiHandler
import com.kaycode.core.network.retrofit.api.ApiService
import com.kaycode.core.network.retrofit.provider.IRetrofitProvider
import com.kaycode.core.network.retrofit.provider.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitProvider(retrofitProvider: RetrofitProvider): IRetrofitProvider = retrofitProvider

    @Provides
    @Singleton
    fun provideApiService(retrofitProvider: IRetrofitProvider): ApiService =
        retrofitProvider.getRetrofit().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHandler(apiHandler: ApiHandler): IApiHandler = apiHandler
}