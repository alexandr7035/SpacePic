package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodsDomain
import java.lang.Exception

interface ApodsDataToDomainMapper: Abstract.Mapper {
    fun map(apods: List<ApodData>): ApodsDomain

    fun map(e: Exception): ApodsDomain
}