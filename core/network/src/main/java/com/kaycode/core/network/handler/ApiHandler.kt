package com.kaycode.core.network.handler

import com.kaycode.core.network.retrofit.model.ApiResponse
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ApiHandler @Inject constructor(

): IApiHandler {
    override suspend fun <T : Any> handleApi(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = apiCall()
            val body = response.body()

            if(response.isSuccessful && body != null) {
                ApiResponse.Success(data = body)
            } else {
                ApiResponse.Error(code = response.code(), message = response.errorBody()?.string() ?: response.message())
            }
        } catch(e: HttpException) {
            ApiResponse.Error(code = e.code(), message = e.response()?.errorBody()?.string() ?: e.message())
        } catch(e: Throwable) {
            ApiResponse.Exception(e)
        }
    }
}