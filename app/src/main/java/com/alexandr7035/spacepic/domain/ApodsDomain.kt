package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.data.ApodData
import com.alexandr7035.spacepic.data.ApodDataToDomainMapper
import com.alexandr7035.spacepic.ui.ApodsUi
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

abstract class ApodsDomain: Abstract.Object<ApodsUi, ApodsDomainToUiMapper>() {
    class Success(private val apods: List<ApodData>, private val apodDataToDomainMapper: ApodDataToDomainMapper): ApodsDomain() {
        override fun map(mapper: ApodsDomainToUiMapper): ApodsUi {

            val apodsDomain = apods.map { apodData ->
                apodData.map(apodDataToDomainMapper)
            }

            return mapper.map(apodsDomain)
        }
    }

    class Fail(private val e: Exception): ApodsDomain() {
        override fun map(mapper: ApodsDomainToUiMapper): ApodsUi {

            val errorType = when (e) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is ConnectException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }

            throw e

            return mapper.map(errorType)
        }

    }
}