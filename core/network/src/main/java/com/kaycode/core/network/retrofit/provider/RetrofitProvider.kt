package com.kaycode.core.network.retrofit.provider

import com.kaycode.core.network.retrofit.utils.NetworkConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitProvider @Inject constructor(

): IRetrofitProvider {

    override fun getRetrofit(): Retrofit = createRetrofit()

    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(NetworkConstants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
