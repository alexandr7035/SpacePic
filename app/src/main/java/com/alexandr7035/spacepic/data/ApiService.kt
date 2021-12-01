package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.data.remote.ApodRemote
import retrofit2.http.GET
import retrofit2.http.Query

// Base url: https://api.nasa.gov/
interface ApiService {
    @GET("/planetary/apod")
    suspend fun getApodsList(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String,
        @Query("thumbs") includeThumbs: Boolean = true
    ): List<ApodRemote>
}