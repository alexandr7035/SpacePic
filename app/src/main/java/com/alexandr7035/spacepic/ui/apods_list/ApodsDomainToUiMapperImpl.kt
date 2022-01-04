package com.alexandr7035.spacepic.ui.apods_list

import com.alexandr7035.spacepic.R
import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.domain.ApodDomain
import com.alexandr7035.spacepic.domain.ApodDomainToUiMapper
import com.alexandr7035.spacepic.domain.ApodsDomainToUiMapper
import com.alexandr7035.spacepic.domain.ResourceProvider
import timber.log.Timber

class ApodsDomainToUiMapperImpl(private val apodDomainToUiMapper: ApodDomainToUiMapper, private val resourceProvider: ResourceProvider): ApodsDomainToUiMapper {
    override fun map(apods: List<ApodDomain>): ApodsUi {
        val apodsUi = apods.map { apodDomain ->
            apodDomain.map(apodDomainToUiMapper)
        }

        return ApodsUi.Success(apodsUi)
    }

    override fun map(errorType: ErrorType): ApodsUi {
        // FIXME
        Timber.debug("ui exception ${errorType.name}")

//        val errorType = when (errorType) {
//            is ErrorType.NO_CONNECTION -> a
//        }

        val errorMessage = when (errorType) {
            ErrorType.NO_CONNECTION -> {
                resourceProvider.getString(R.string.error_no_connection)
            }
            ErrorType.SERVICE_UNAVAILABLE -> {
                resourceProvider.getString(R.string.error_service_unavailable)
            }
            ErrorType.TIMEOUT_ERROR -> {
                resourceProvider.getString(R.string.error_timeout)
            }
            ErrorType.GENERIC_ERROR -> {
                resourceProvider.getString(R.string.error_unknown)
            }
        }

        return ApodsUi.Fail(errorMessage)
    }
}