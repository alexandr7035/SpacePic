package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.domain.ApodsDomain

abstract class ApodsData: Abstract.Object<ApodsDomain, ApodsDataToDomainMapper>() {
    data class Success(private val apods: List<ApodData>): ApodsData() {
        override fun map(mapper: ApodsDataToDomainMapper): ApodsDomain {
            return mapper.map(apods)
        }

    }

    data class Fail(private val e: Exception): ApodsData() {
        override fun map(mapper: ApodsDataToDomainMapper): ApodsDomain {
            return mapper.map(e)
        }

    }
}