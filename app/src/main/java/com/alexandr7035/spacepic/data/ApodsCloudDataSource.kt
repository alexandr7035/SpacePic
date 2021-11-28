package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.data.remote.ApodRemote

interface ApodsCloudDataSource {
    suspend fun fetchApods(): List<ApodRemote>
}