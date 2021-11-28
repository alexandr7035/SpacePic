package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.data.ApodData
import com.alexandr7035.spacepic.data.ApodsDataToDomainMapper
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

class ApodsDataToDomainMapperImpl(private val apodsDomainToUiMapper: ApodsDomainToUiMapper): ApodsDataToDomainMapper {
    override fun map(apods: List<ApodData>): ApodsDomain {
        return ApodsDomain.Success(apods)
    }

    override fun map(e: Exception): ApodsDomain {
//        val errorType = when (e) {
//            is UnknownHostException -> ErrorType.NO_CONNECTION
//            is ConnectException -> ErrorType.NO_CONNECTION
//            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
//            else -> ErrorType.GENERIC_ERROR
//        }

        return ApodsDomain.Fail(e)
    }
}