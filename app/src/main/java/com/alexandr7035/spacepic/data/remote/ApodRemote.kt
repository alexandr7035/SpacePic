package com.alexandr7035.spacepic.data.remote

import com.alexandr7035.spacepic.core.Abstract
import com.alexandr7035.spacepic.data.ApodData
import com.google.gson.annotations.SerializedName

class ApodRemote(
    @SerializedName("media_type")
    private val mediaType: String,
    private val title: String,
    @SerializedName("url")
    private val apodUrl: String,
    private val date: String,
    @SerializedName("explanation")
    private val description: String,
    @SerializedName("thumbnail_url")
    private val thumbUrl: String?,
): Abstract.Object<ApodData, ApodRemoteToDataMapper>() {
    override fun map(mapper: ApodRemoteToDataMapper): ApodData {
        return mapper.map(
            mediaType = mediaType,
            title = title,
            description = description,
            date = date,
            apodUrl = apodUrl,
            videoThumbUrl = thumbUrl
        )
    }
}