package com.alexandr7035.spacepic.data

interface ApodsRepository {
    suspend fun fetchPictures(startDate: String, endDate: String): ApodsData

    suspend fun fetchApod(date: String): ApodData
}