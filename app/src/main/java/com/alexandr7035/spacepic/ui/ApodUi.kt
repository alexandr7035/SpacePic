package com.alexandr7035.spacepic.ui

abstract class ApodUi {
    class ImageApod(
        val title: String,
        val apodUri: String,
        val date: String,
        val description: String
    ): ApodUi()

    class VideoApod(

    ): ApodUi()

    class Fail(val errorMessage: String): ApodUi()
}