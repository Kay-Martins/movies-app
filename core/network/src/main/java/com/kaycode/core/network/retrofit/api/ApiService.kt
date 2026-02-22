package com.kaycode.core.network.retrofit.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun get(@Url url: String, @HeaderMap headerMap: Map<String, String>): Response<String>
}