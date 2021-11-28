package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.data.ApodData

class ApodRemoteToDataMapperImpl: ApodRemoteToDataMapper {
    override fun map(title: String, remoteUrl: String, date: String, description: String): ApodData {
        return ApodData(title = title, apodUri = remoteUrl, date = date, description = description)
    }
}