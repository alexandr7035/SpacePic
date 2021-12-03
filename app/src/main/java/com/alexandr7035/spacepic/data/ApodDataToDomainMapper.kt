package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodDomain

interface ApodDataToDomainMapper: Abstract.Mapper {
    fun map(
        mediaType: String,
        title: String,
        apodUrl: String,
        date: String,
        description: String,
        videoThumbUrl: String?
    ): ApodDomain

    fun map(e: Exception): ApodDomain
}