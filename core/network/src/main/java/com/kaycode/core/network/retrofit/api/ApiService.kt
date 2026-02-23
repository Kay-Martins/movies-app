package com.kaycode.core.network.retrofit.api

import com.kaycode.core.model.DiscoverMovieResponse
import com.kaycode.core.network.retrofit.utils.NetworkConstants.DISCOVER_MOVIE_PATH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiService {
    @GET(DISCOVER_MOVIE_PATH)
    suspend fun get(
        @HeaderMap headerMap: Map<String, String>,
        @QueryMap queryMap: Map<String, Any>
    ): Response<DiscoverMovieResponse>
}