package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.ErrorType
import com.alexandr7035.spacepic.data.ApodData
import com.alexandr7035.spacepic.data.ApodDataToDomainMapper
import com.alexandr7035.spacepic.data.ApodsDataToDomainMapper
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

class ApodsDataToDomainMapperImpl(private val apodDataToDomainMapper: ApodDataToDomainMapper): ApodsDataToDomainMapper {
    override fun map(apods: List<ApodData>): ApodsDomain {
        return ApodsDomain.Success(apods, apodDataToDomainMapper)
    }

    override fun map(e: Exception): ApodsDomain {
        return ApodsDomain.Fail(e)
    }
}