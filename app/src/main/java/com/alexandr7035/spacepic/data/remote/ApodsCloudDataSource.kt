package com.alexandr7035.spacepic.data.remote

interface ApodsCloudDataSource {
    suspend fun fetchApods(startDate: String, endDate: String): List<ApodRemote>
}