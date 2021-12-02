package com.alexandr7035.spacepic.domain

interface ApodsInteractor {
    // FIXME use long after data layer
    suspend fun fetchApods(lastApodDate: Long): ApodsDomain
}