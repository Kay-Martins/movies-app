package com.kaycode.core.utils

import javax.inject.Inject

class ImageUtils @Inject constructor(): IImageUtils {
    override fun buildFinalImageUrl(baseUrl: String, imagePath: String): String =
        "$baseUrl$imagePath"
}
