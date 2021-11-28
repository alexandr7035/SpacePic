package com.alexandr7035.spacepic.domain

interface ApodsInteractor {
    suspend fun fetchApods(): ApodsDomain
}