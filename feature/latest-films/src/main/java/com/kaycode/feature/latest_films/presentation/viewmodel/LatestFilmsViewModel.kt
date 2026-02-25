package com.kaycode.feature.latest_films.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaycode.core.model.DiscoverMovieResponse
import com.kaycode.core.network.retrofit.model.ApiResponse
import com.kaycode.feature.latest_films.R
import com.kaycode.feature.latest_films.manager.ILatestFilmsManager
import com.kaycode.feature.latest_films.provider.IMovieThumbnailListProvider
import com.kaycode.feature.latest_films.provider.MovieThumbnailListProvider
import com.kaycode.feature.latest_films.usecase.IGetLatestFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestFilmsViewModel @Inject constructor(
    private val getLatestFilmsUseCase: IGetLatestFilmsUseCase,
    private val movieThumbnailListProvider: IMovieThumbnailListProvider,
    private val latestFilmsManager: ILatestFilmsManager
): ViewModel(), LatestFilmsViewModelInputs, LatestFilmsViewModelOutputs {

    val outputs: LatestFilmsViewModelOutputs = this
    val inputs: LatestFilmsViewModelInputs = this

    init {
        viewModelScope.launch {
            _latestFilmsState.emit(LatestFilmsState.Init)

        }
    }

    private val _latestFilmsState: MutableStateFlow<LatestFilmsState> =
        MutableStateFlow(LatestFilmsState.Loading)
    override val latestFilmsState: StateFlow<LatestFilmsState>
        get() = _latestFilmsState
    private val _latestFilmsEffects = MutableSharedFlow<LatestFilmsEffects>()
    override val latestFilmsEffects: SharedFlow<LatestFilmsEffects>
        get() = _latestFilmsEffects

    override fun init() {
        viewModelScope.launch {
            _latestFilmsState.emit(LatestFilmsState.Init)
        }
    }

    override fun onStartClick() { fetchAndDisplayLatestFilms() }

    override fun onNextPageClick() {
        if(!latestFilmsManager.isOnLastPage()) {
            fetchAndDisplayLatestFilms(latestFilmsManager.currentPage.value + 1)
            latestFilmsManager.updateData(newPage = latestFilmsManager.currentPage.value + 1)
        }
    }

    override fun onPreviousPageClick() {
        if(!latestFilmsManager.isOnFirstPage()) {
            fetchAndDisplayLatestFilms(latestFilmsManager.currentPage.value - 1)
            latestFilmsManager.updateData(newPage = latestFilmsManager.currentPage.value - 1)
        }
    }

    override fun onMovieThumbnailClick(id: Int) {
        viewModelScope.launch {
            _latestFilmsEffects.emit(LatestFilmsEffects.NavigateToFilmDetails(id))
        }
    }

    private fun fetchAndDisplayLatestFilms(page: Int = DEFAULT_PAGE_NUMBER) {
        viewModelScope.launch {
            _latestFilmsState.emit(LatestFilmsState.Loading)
            // this will get the first page of film results
            when(val response: ApiResponse<DiscoverMovieResponse> = getLatestFilmsUseCase(page)) {
                is ApiResponse.Success -> {
                    val filmsData = response.data.results
                    val currentPage = response.data.page
                    val totalPages = response.data.totalPages
                    latestFilmsManager.updateData(currentPage, totalPages)
                    val movieThumbnails = movieThumbnailListProvider.provideMovieThumbnailList(filmsData)
                    _latestFilmsState.emit(
                        LatestFilmsState.Success(
                            films = movieThumbnails,
                            isOnFirstPage = latestFilmsManager.isOnFirstPage(),
                            isOnLastPage = latestFilmsManager.isOnLastPage()
                        )
                    )
                }
                is ApiResponse.Error -> _latestFilmsState.emit(LatestFilmsState.Error(R.string.error_message))
                is ApiResponse.Exception -> _latestFilmsState.emit(LatestFilmsState.Error(R.string.error_message))
            }
        }
    }

    companion object {
        private const val DEFAULT_PAGE_NUMBER = 1
    }
}
