package com.alexandr7035.spacepic.data

interface ApodsRepository {
    suspend fun fetchPictures(): ApodsData
}