package com.kaycode.feature.latest_films.data

import androidx.core.os.BuildCompat
import com.kaycode.core.model.DiscoverMovieParams
import com.kaycode.core.model.DiscoverMovieResponse
import com.kaycode.core.network.BuildConfig
import com.kaycode.core.network.handler.IApiHandler
import com.kaycode.core.network.retrofit.api.ApiService
import com.kaycode.core.network.retrofit.model.ApiResponse
import com.kaycode.core.utils.IDateUtils
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.AUTHORISATION_HEADER_KEY
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.AUTHORISATION_HEADER_VALUE
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.CONTENT_TYPE_HEADER_KEY
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.CONTENT_TYPE_HEADER_VALUE
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.INCLUDE_ADULT_PARAM_KEY
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.INCLUDE_VIDEO_PARAM_KEY
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.LANGUAGE_PARAM
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.PAGE_PARAM
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.PRIMARY_RELEASE_DATE_GTE_PARAM
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.PRIMARY_RELEASE_DATE_LTE_PARAM
import com.kaycode.feature.latest_films.utils.LatestFilmsNetworkConstants.SORT_BY_PARAM
import javax.inject.Inject

class LatestFilmsRepository @Inject constructor(
    private val apiService: ApiService,
    private val apiHandler: IApiHandler,
    private val dateUtils: IDateUtils
): ILatestFilmsRepository {
    override suspend fun getLatestFilms(page: Int): ApiResponse<DiscoverMovieResponse> =
        apiHandler.handleApi {
            apiService.get(
                headerMap = getHeaderMap(),
                queryMap = getQueryMap(page)
            )
        }

    private fun getHeaderMap() =
        mutableMapOf(
            AUTHORISATION_HEADER_KEY to getAuthorisationValue(),
            CONTENT_TYPE_HEADER_KEY to CONTENT_TYPE_HEADER_VALUE
        )

    private fun getQueryMap(page: Int): MutableMap<String, Any> {
        val queryValues = DiscoverMovieParams(
            primaryReleaseDateGte = dateUtils.getThreeMonthsAgoDateInYyyymmdd(),
            primaryReleaseDateLte = dateUtils.getCurrentDateInYyyymmdd()
        )
        return mutableMapOf(
            INCLUDE_ADULT_PARAM_KEY to queryValues.includeAdult,
            INCLUDE_VIDEO_PARAM_KEY to queryValues.includeVideo,
            LANGUAGE_PARAM to queryValues.language,
            PAGE_PARAM to page.toString(),
            PRIMARY_RELEASE_DATE_GTE_PARAM to queryValues.primaryReleaseDateGte,
            PRIMARY_RELEASE_DATE_LTE_PARAM to queryValues.primaryReleaseDateLte,
            SORT_BY_PARAM to queryValues.sortBy
        )
    }

    private fun getAuthorisationValue() =
        "$AUTHORISATION_HEADER_VALUE${BuildConfig.API_KEY}"
}
