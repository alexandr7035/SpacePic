package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodDomain

class ApodData(
    private val mediaType: String,
    private val title: String,
    private val apodUrl: String,
    private val date: String,
    private val description: String,
    private val videoThumbUrl: String?): Abstract.Object<ApodDomain, ApodDataToDomainMapper>() {

    override fun map(mapper: ApodDataToDomainMapper): ApodDomain {
        return mapper.map(
            mediaType = mediaType,
            title = title,
            apodUrl = apodUrl,
            date = date,
            description = description,
            videoThumbUrl = videoThumbUrl
        )
    }
}