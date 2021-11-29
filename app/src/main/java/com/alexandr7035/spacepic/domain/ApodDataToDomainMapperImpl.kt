package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.data.ApodDataToDomainMapper

class ApodDataToDomainMapperImpl: ApodDataToDomainMapper {
    override fun map(title: String, apodUri: String, date: String, description: String): ApodDomain {
        // FIXME
        return ApodDomain.ImageApod(
            title = title,
            apodUri = apodUri,
            date = date,
            description = description
        )
    }
}