package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.data.remote.ApodRemote
import javax.inject.Inject

class CloudDataSourceImpl @Inject constructor(private val apiService: ApiService): CloudDataSource {
    override suspend fun fetchApods(): List<ApodRemote> {
        return apiService.getApodsList(
            startDate = "2021-10-01",
            endDate = "2021-10-31",
            apiKey = "DEMO_KEY"
        )
    }
}