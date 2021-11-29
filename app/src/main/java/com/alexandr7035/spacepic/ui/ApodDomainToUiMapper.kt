package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper

class ApodDomainToUiMapperImpl: ApodDomainToUiMapper {
    override fun map(title: String, apodUri: String, date: String, description: String): ApodUi {
        return ApodUi.ImageApod(title, apodUri, date, description)
    }
}