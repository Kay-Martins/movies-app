package com.kaycode.feature.latest_films.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kaycode.core.model.MovieThumbnail
import com.kaycode.core.ui.CoilAsyncImageComponent
import com.kaycode.core.ui.Padding
import com.kaycode.feature.latest_films.R
import com.kaycode.feature.latest_films.presentation.viewmodel.LatestFilmsState

private const val ITEM_HEIGHT = 100
private const val ITEM_WIDTH = 35

@Composable
fun LatestFilmsScreen(
    uiState: LatestFilmsState,
    onStartClick: () -> Unit,
    onNextPageClick: () -> Unit,
    onPreviousPageClick: () -> Unit,
    onMovieThumbnailClick: (Int) -> Unit
) {
    val gridState = rememberLazyGridState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(PaddingValues(Padding.MEDIUM.dp))
    ) {

        when(uiState) {
            is LatestFilmsState.Init -> { LatestFilmsInitScreenComponent(onStartClick) }
            is LatestFilmsState.Loading -> { LatestFilmsLoadingScreenComponent() }
            is LatestFilmsState.Success -> {
                LatestFilmsScrollGridComponent(
                    gridState = gridState,
                    movieList = uiState.films,
                    onNextPageClick = onNextPageClick,
                    onPreviousPageClick = onPreviousPageClick,
                    onMovieThumbnailClick = onMovieThumbnailClick,
                    isNextPageButtonEnabled = !uiState.isOnLastPage,
                    isPreviousPageButtonEnabled = !uiState.isOnFirstPage
                )
            }
            is LatestFilmsState.Error -> { LatestFilmsErrorScreenComponent(uiState.message) }
        }
    }
}

@Composable
private fun LatestFilmsInitScreenComponent(
    onStartClick: () -> Unit
) {
    LatestFilmsContentContainer {
        Text(text = stringResource(id = R.string.get_started))
        Spacer(modifier = Modifier.height(Padding.SMALL.dp))
        Button(
            onClick = { onStartClick() }
        ) { Text(text = stringResource(id = R.string.see_films)) }
    }
}

@Composable
private fun LatestFilmsLoadingScreenComponent() {
    LatestFilmsContentContainer {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
        Spacer(modifier = Modifier.height(Padding.SMALL.dp))
        Text(text = stringResource(id = R.string.loading_message))
    }
}

@Composable
private fun LatestFilmsErrorScreenComponent(errorMessageId: Int) {
    LatestFilmsContentContainer {
        Text(text = stringResource(id = errorMessageId))
    }
}

@Composable
private fun LatestFilmsContentContainer(content : @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

@Composable
private fun MovieThumbnailComponent(
    movieThumbnail: MovieThumbnail,
    onMovieThumbnailClick: (Int) -> Unit
) {
    CoilAsyncImageComponent(
        url = movieThumbnail.posterPath,
        modifier = Modifier
            .width(ITEM_WIDTH.dp)
            .height(ITEM_HEIGHT.dp)
            .clickable {
                onMovieThumbnailClick(movieThumbnail.id)
            }
    )
}

@Composable
private fun LatestFilmsScrollGridComponent(
    gridState: LazyGridState,
    movieList: List<MovieThumbnail>,
    onNextPageClick: () -> Unit,
    onPreviousPageClick: () -> Unit,
    onMovieThumbnailClick: (Int) -> Unit,
    isNextPageButtonEnabled: Boolean,
    isPreviousPageButtonEnabled: Boolean
) {
    LatestFilmsContentContainer {
        LazyVerticalGrid(
            state = gridState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.75f),
            columns = GridCells.Adaptive(ITEM_WIDTH.dp)
        ) {
            items(items = movieList, key = {it.id}) {
                MovieThumbnailComponent(
                    movieThumbnail = it,
                    onMovieThumbnailClick = onMovieThumbnailClick
                )

            }
        }
    }
    PreviousAndNextButtonsComponent(
        onNextPageClick = { onNextPageClick() },
        onPreviousPageClick = { onPreviousPageClick() } ,
        isNextPageButtonEnabled = isNextPageButtonEnabled,
        isPreviousPageButtonEnabled = isPreviousPageButtonEnabled
    )
}

@Composable
private fun PreviousAndNextButtonsComponent(
    onNextPageClick: () -> Unit,
    onPreviousPageClick: () -> Unit,
    isNextPageButtonEnabled: Boolean,
    isPreviousPageButtonEnabled: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { onPreviousPageClick() },
            enabled = isPreviousPageButtonEnabled
        ) {
            Text(stringResource(R.string.previous_page))
        }
        Button(
            onClick = { onNextPageClick() },
            enabled = isNextPageButtonEnabled
        ) {
            Text(stringResource(R.string.next_page))
        }
    }
}
