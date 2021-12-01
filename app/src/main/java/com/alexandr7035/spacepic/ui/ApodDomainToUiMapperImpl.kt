package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper

class ApodDomainToUiMapperImpl: ApodDomainToUiMapper {
    override fun mapImage(title: String, imageUrl: String, date: String, description: String): ApodUi {
        return ApodUi.ImageApod(title = title, imageUrl = imageUrl, date = date, description = description)
    }

    override fun mapVideo(title: String, videoThumbUrl: String, date: String, description: String): ApodUi {
        return ApodUi.VideoApod(title = title, videoThumbUrl = videoThumbUrl, date = date, description = description)
    }
}