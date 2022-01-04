package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper
import com.alexandr7035.spacepic.domain.ResourceProvider
import javax.inject.Inject

class ApodDomainToUiMapperImpl @Inject constructor(private val resourceProvider: ResourceProvider): ApodDomainToUiMapper {
    override fun mapImage(title: String, imageUrl: String, date: Long, description: String): ApodUi {
        return ApodUi.ImageApod(title = title, imageUrl = imageUrl, date = date, description = description)
    }

    override fun mapVideo(title: String, videoThumbUrl: String, date: Long, description: String): ApodUi {
        return ApodUi.VideoApod(title = title, videoThumbUrl = videoThumbUrl, date = date, description = description)
    }

    override fun mapFail(errorType: ErrorType): ApodUi {
        val errorMessage = when (errorType) {
            ErrorType.NO_CONNECTION -> {
                resourceProvider.getString(R.string.error_no_connection)
            }
            ErrorType.SERVICE_UNAVAILABLE -> {
                resourceProvider.getString(R.string.error_service_unavailable)
            }
            ErrorType.TIMEOUT_ERROR -> {
                resourceProvider.getString(R.string.error_timeout)
            }
            ErrorType.GENERIC_ERROR -> {
                resourceProvider.getString(R.string.error_unknown)
            }
        }

        return ApodUi.Fail(errorMessage)
    }
}