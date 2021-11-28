package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.data.ApodData

interface ApodListRemoteToDataMapper: Abstract.Mapper {
    fun map(apods: List<ApodRemote>): List<ApodData>
}