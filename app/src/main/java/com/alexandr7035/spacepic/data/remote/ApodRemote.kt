package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.data.ApodData

class ApodRemote(
    private val title: String,
    private val remoteUrl: String,
    private val date: String,
    private val description: String
): Abstract.Object<ApodData, ApodRemoteToDataMapper>() {
    override fun map(mapper: ApodRemoteToDataMapper): ApodData {
        return mapper.map(title, description, date, remoteUrl)
    }
}