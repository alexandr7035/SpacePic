package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.BuildConfig
import com.alexandr7035.spacepic.data.remote.ApodRemote
import javax.inject.Inject

class ApodsCloudDataSourceImpl @Inject constructor(private val apiService: ApiService): ApodsCloudDataSource {
    override suspend fun fetchApods(startDate: String, endDate: String): List<ApodRemote> {
        return apiService.getApodsList(
            startDate = startDate,
            endDate = endDate,
            apiKey = BuildConfig.API_KEY
        ).reversed()
    }
}