package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.data.ApodData
import com.google.gson.annotations.SerializedName

class ApodRemote(
    private val title: String,
    @SerializedName("url")
    private val remoteUrl: String,
    private val date: String,
    @SerializedName("explanation")
    private val description: String
): Abstract.Object<ApodData, ApodRemoteToDataMapper>() {
    override fun map(mapper: ApodRemoteToDataMapper): ApodData {
        return mapper.map(title = title, description = description, date = date, remoteUrl = remoteUrl)
    }
}