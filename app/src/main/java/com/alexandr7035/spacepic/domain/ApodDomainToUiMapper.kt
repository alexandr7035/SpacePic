package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.ui.ApodUi

interface ApodDomainToUiMapper: Abstract.Mapper {
    // FIXME
    fun mapImage(title: String,
            imageUrl: String,
            date: Long,
            description: String): ApodUi

    fun mapVideo(title: String,
            videoThumbUrl: String,
            date: Long,
            description: String): ApodUi

    fun mapFail(errorType: ErrorType): ApodUi
}