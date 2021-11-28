package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.data.ApodData

class ApodListRemoteToDataMapperImpl(private val apodRemoteToDataMapper: ApodRemoteToDataMapper): ApodListRemoteToDataMapper {
    override fun map(apods: List<ApodRemote>): List<ApodData> {
        return apods.map { apodRemote ->
            apodRemote.map(apodRemoteToDataMapper)
        }
    }
}