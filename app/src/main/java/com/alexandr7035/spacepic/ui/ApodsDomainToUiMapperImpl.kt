package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.domain.ApodDomain
import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper
import com.alexandr7035.spacepic.domain.ApodsDomainToUiMapper

class ApodsDomainToUiMapperImpl(private val apodDomainToUiMapper: ApodDomainToUiMapper): ApodsDomainToUiMapper {
    override fun map(apods: List<ApodDomain>): ApodsUi {
        val apodsUi = apods.map { apodDomain ->
            apodDomain.map(apodDomainToUiMapper)
        }

        return ApodsUi.Success(apodsUi)
    }

    override fun map(errorType: ErrorType): ApodsUi {
        // FIXME
        return ApodsUi.Fail("Error message")
    }
}