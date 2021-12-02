package com.alexandr7035.spacepic.ui

abstract class ApodUi {
    class ImageApod(
        val title: String,
        val imageUrl: String,
        val date: String,
        val description: String
    ): ApodUi()

    class VideoApod(
        val title: String,
        val videoThumbUrl: String,
        val date: String,
        val description: String
    ): ApodUi()

    class LoadingFooter(): ApodUi()
}