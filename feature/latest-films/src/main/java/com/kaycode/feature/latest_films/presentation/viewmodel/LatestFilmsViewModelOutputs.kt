package com.kaycode.feature.latest_films.presentation.viewmodel

import com.kaycode.core.model.MovieThumbnail
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface LatestFilmsViewModelOutputs {
    val latestFilmsState: StateFlow<LatestFilmsState>
    val latestFilmsEffects: SharedFlow<LatestFilmsEffects>
}

sealed class LatestFilmsState {
    data object Init: LatestFilmsState()
    data object Loading: LatestFilmsState()
    data class Success(
        val films: List<MovieThumbnail>,
        val isOnFirstPage: Boolean,
        val isOnLastPage: Boolean
    ): LatestFilmsState()
    data class Error(val message: String): LatestFilmsState()
}

sealed class LatestFilmsEffects {
    data class NavigateToFilmDetails(val id: Int): LatestFilmsEffects()
}