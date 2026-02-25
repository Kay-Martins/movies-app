package com.kaycode.feature.latest_films.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kaycode.core.ui.theme.MoviesAppTheme
import com.kaycode.feature.latest_films.presentation.component.LatestFilmsScreen
import com.kaycode.feature.latest_films.presentation.viewmodel.LatestFilmsEffects
import kotlinx.coroutines.launch
import com.kaycode.feature.latest_films.presentation.viewmodel.LatestFilmsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LatestFilmsActivity: ComponentActivity() {

    private val viewModel: LatestFilmsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLatestFilmsEffects()
        viewModel.inputs.init()
        enableEdgeToEdge()
        setContent {
            MoviesAppTheme {
                LatestFilmsScreen(
                    uiState = viewModel.outputs.latestFilmsState.collectAsState().value,
                    onStartClick = { viewModel.inputs.onStartClick() },
                    onNextPageClick = { viewModel.inputs.onNextPageClick() },
                    onPreviousPageClick = { viewModel.inputs.onPreviousPageClick() },
                    onMovieThumbnailClick = viewModel.inputs::onMovieThumbnailClick
                )
            }
        }

    }

    private fun observeLatestFilmsEffects() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.outputs.latestFilmsEffects.collect { effect ->
                    when(effect) {
                        is LatestFilmsEffects.NavigateToFilmDetails -> {
                            // do something with the ID here. Ideally switch to a new activity and display movie details
                        }
                    }
                }
            }
        }
    }


}