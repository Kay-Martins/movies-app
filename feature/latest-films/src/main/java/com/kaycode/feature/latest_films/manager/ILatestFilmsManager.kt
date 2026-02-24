package com.kaycode.feature.latest_films.manager

import kotlinx.coroutines.flow.StateFlow

interface ILatestFilmsManager {
    val currentPage: StateFlow<Int>
    val totalPages: StateFlow<Int>
    fun updateData(newPage: Int? = null, newTotalPages: Int? = null)
    fun isOnFirstPage(): Boolean
    fun isOnLastPage(): Boolean
}