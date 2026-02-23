package com.kaycode.feature.latest_films.utils

object LatestFilmsNetworkConstants {
    const val INCLUDE_ADULT_PARAM_KEY = "include_adult"
    const val INCLUDE_VIDEO_PARAM_KEY = "include_video"
    const val LANGUAGE_PARAM = "language"
    const val LANGUAGE_VALUE = "en-US"
    const val PAGE_PARAM = "page"
    const val PRIMARY_RELEASE_DATE_GTE_PARAM = "primary_release_date.gte"
    const val PRIMARY_RELEASE_DATE_LTE_PARAM = "primary_release_date.lte"
    const val SORT_BY_PARAM = "sort_by"
    const val SORT_BY_VALUE = "primary_release_date.desc"

    const val AUTHORISATION_HEADER_KEY = "Authorization"
    const val CONTENT_TYPE_HEADER_KEY = "accept"
    const val AUTHORISATION_HEADER_VALUE = "Bearer "
    const val CONTENT_TYPE_HEADER_VALUE = "application/json"
    // ?include_adult=false&include_video=false&language=en-US&page=1&primary_release_date.gte=2025-11-01&primary_release_date.lte=2026-02-22&sort_by=primary_release_date.desc"
}