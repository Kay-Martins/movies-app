package com.kaycode.core.network.retrofit.provider

import retrofit2.Retrofit

interface IRetrofitProvider {
    fun getRetrofit(): Retrofit
}