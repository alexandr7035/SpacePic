package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.data.ApodData

class ApodRemoteToDataMapperImpl: ApodRemoteToDataMapper {

    override fun map(
        mediaType: String,
        title: String,
        apodUrl: String,
        date: String,
        description: String,
        videoThumbUrl: String?
    ): ApodData {
        return ApodData.Base(
            mediaType = mediaType,
            title = title,
            apodUrl = apodUrl,
            date = date,
            description = description,
            videoThumbUrl = videoThumbUrl
        )
    }

}