package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.ui.ApodUi

interface ApodDomainToUiMapper: Abstract.Mapper {
    fun map(title: String,
            apodUri: String,
            date: String,
            description: String): ApodUi.ImageApod
}