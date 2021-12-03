package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.ui.ApodUi
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class ApodDomain(): Abstract.Object<ApodUi, ApodDomainToUiMapper>() {
    class ImageApod(
        private val title: String,
        private val imageUrl: String,
        private val date: String,
        private val description: String
    ): ApodDomain() {

        override fun map(mapper: ApodDomainToUiMapper): ApodUi {
            return mapper.mapImage(title, imageUrl, date, description)
        }
    }

    class VideoApod(
        private val title: String,
        private val videoThumbUrl: String,
        private val date: String,
        private val description: String
    ): ApodDomain() {
        override fun map(mapper: ApodDomainToUiMapper): ApodUi {
            return mapper.mapVideo(title, videoThumbUrl, date, description)
        }
    }

    class Fail(private val e: Exception): ApodDomain() {
        override fun map(mapper: ApodDomainToUiMapper): ApodUi {

            val errorType = when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is ConnectException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                is SocketTimeoutException -> ErrorType.TIMEOUT_ERROR
                else -> ErrorType.GENERIC_ERROR
            }

            return mapper.mapFail(errorType)
        }
    }
}