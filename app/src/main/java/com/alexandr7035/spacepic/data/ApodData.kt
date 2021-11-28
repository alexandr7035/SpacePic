package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodDomain

class ApodData(
    private val title: String,
    private val apodUri: String,
    private val date: String,
    private val description: String): Abstract.Object<ApodDomain, ApodDataToDomainMapper>() {

    override fun map(mapper: ApodDataToDomainMapper): ApodDomain {
        return mapper.map(title, apodUri, date, description)
    }
}