package com.kaycode.feature.latest_films.manager

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LatestFilmsManager @Inject constructor(): ILatestFilmsManager {

    private val _currentPage: MutableStateFlow<Int> = MutableStateFlow(INITIAL_PAGE_NUMBER)
    override val currentPage: StateFlow<Int>
        get() = _currentPage
    private val _totalPages: MutableStateFlow<Int> = MutableStateFlow(INITIAL_TOTAL_PAGES)
    override val totalPages: StateFlow<Int>
        get() = _totalPages

    override fun updateData(newPage: Int?, newTotalPages: Int?) {
        newPage?.let {
            _currentPage.update { it }
        }
        newTotalPages?.let {
            _totalPages.update { it }
        }
    }

    override fun isOnFirstPage(): Boolean =
        currentPage.value == INITIAL_PAGE_NUMBER

    override fun isOnLastPage(): Boolean =
        currentPage.value == totalPages.value

    companion object {
        private const val INITIAL_PAGE_NUMBER = 1
        private const val INITIAL_TOTAL_PAGES = 1
    }
}
