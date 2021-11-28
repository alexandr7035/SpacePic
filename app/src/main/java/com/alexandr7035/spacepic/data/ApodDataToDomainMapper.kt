package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodDomain

interface ApodDataToDomainMapper: Abstract.Mapper {
    fun map(
        title: String,
        apodUri: String,
        date: String,
        description: String
    ): ApodDomain
}