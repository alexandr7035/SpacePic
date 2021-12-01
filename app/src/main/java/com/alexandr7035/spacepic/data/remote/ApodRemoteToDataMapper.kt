package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.data.ApodData

interface ApodRemoteToDataMapper: Abstract.Mapper {
    fun map(
        mediaType: String,
        title: String,
        apodUrl: String,
        date: String,
        description: String,
        videoThumbUrl: String?
    ): ApodData
}