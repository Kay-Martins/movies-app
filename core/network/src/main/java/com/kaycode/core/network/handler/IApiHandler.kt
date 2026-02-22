package com.kaycode.core.network.handler

import com.kaycode.core.network.retrofit.model.ApiResponse
import retrofit2.Response

interface IApiHandler {
    suspend fun <T: Any> handleApi(apiCall: suspend () -> Response<T>): ApiResponse<T>
}
