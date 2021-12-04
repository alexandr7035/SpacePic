package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.data.ApodDataToDomainMapper
import timber.log.Timber
import java.lang.RuntimeException

class ApodDataToDomainMapperImpl : ApodDataToDomainMapper {

    override fun map(
        mediaType: String,
        title: String,
        apodUrl: String,
        date: Long,
        description: String,
        videoThumbUrl: String?
    ): ApodDomain {

        // Return video or image here
        Timber.debug(mediaType)

        return when (mediaType) {
            APOD_IMAGE -> {
                ApodDomain.ImageApod(
                    title = title,
                    imageUrl = apodUrl,
                    date = date,
                    description = description
                )
            }

            APOD_VIDEO -> {
                ApodDomain.VideoApod(
                    title = title,
                    videoThumbUrl = videoThumbUrl ?: "",
                    date = date,
                    description = description
                )
            }

            else -> throw RuntimeException("Unknown APOD type. Implement it")

        }

    }

    override fun map(e: Exception): ApodDomain {
        return ApodDomain.Fail(e)
    }

    companion object {
        private const val APOD_IMAGE = "image"
        private const val APOD_VIDEO = "video"
    }
}