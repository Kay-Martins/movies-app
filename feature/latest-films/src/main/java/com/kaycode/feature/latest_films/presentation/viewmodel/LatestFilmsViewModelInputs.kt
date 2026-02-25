package com.kaycode.feature.latest_films.presentation.viewmodel

interface LatestFilmsViewModelInputs {
    fun init()
    fun onStartClick()
    fun onNextPageClick()
    fun onPreviousPageClick()
    fun onMovieThumbnailClick(id: Int)
}