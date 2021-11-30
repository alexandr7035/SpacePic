package com.alexandr7035.spacepic.ui

import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.domain.ApodDomain
import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper
import com.alexandr7035.spacepic.domain.ApodsDomainToUiMapper
import timber.log.Timber

class ApodsDomainToUiMapperImpl(private val apodDomainToUiMapper: ApodDomainToUiMapper): ApodsDomainToUiMapper {
    override fun map(apods: List<ApodDomain>): ApodsUi {
        val apodsUi = apods.map { apodDomain ->
            apodDomain.map(apodDomainToUiMapper)
        }

        return ApodsUi(apodsUi)
    }

    override fun map(errorType: ErrorType): ApodsUi {
        // FIXME
        Timber.debug("ui exception ${errorType.name}")
        return ApodsUi(listOf(ApodUi.Fail("Error message")))
    }
}