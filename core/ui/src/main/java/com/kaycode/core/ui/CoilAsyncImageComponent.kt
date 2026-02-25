package com.kaycode.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.revenuecat.placeholder.placeholder


@Composable
fun CoilAsyncImageComponent(
    url: String?,
    modifier: Modifier = Modifier,
    ) {

    val asyncImage = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build()
    )
    val imageState by asyncImage.state.collectAsState()
    val sizeResolver = rememberConstraintsSizeResolver()

    when(imageState) {
        is AsyncImagePainter.State.Success -> {
            Image(
                painter = asyncImage,
                contentDescription = null,
                modifier = modifier.then(sizeResolver)
            )
        }
        is AsyncImagePainter.State.Error -> {
            ImageLoadErrorComponent(modifier)
        }
        is AsyncImagePainter.State.Loading -> {
            PlaceholderComponent(modifier)
        }
        else -> { }
    }
}

@Composable
private fun PlaceholderComponent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .placeholder(),
        content = { }
    )
}

@Composable
private fun ImageLoadErrorComponent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Text(stringResource(R.string.image_load_error))
    }
}
