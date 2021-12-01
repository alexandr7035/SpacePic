package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.data.ApodDataToDomainMapper
import timber.log.Timber
import java.lang.RuntimeException

class ApodDataToDomainMapperImpl: ApodDataToDomainMapper {
    override fun map(
        mediaType: String,
        title: String,
        apodUrl: String,
        date: String,
        description: String,
        videoThumbUrl: String?
    ): ApodDomain {

        // Return video or image here
        Timber.debug(mediaType)

        when (mediaType) {
            "image" -> {
                return ApodDomain.ImageApod(
                    title = title,
                    imageUrl = apodUrl,
                    date = date,
                    description = description
                )
            }

            "video" -> {
                return ApodDomain.VideoApod(
                    title = title,
                    videoThumbUrl = videoThumbUrl ?: "",
                    date = date,
                    description = description
                )
            }

            else -> {
                throw RuntimeException("Unknown APOD type")
            }
        }

    }
}