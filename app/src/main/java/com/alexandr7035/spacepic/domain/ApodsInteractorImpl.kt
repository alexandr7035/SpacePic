package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.data.ApodsDataToDomainMapper
import com.alexandr7035.spacepic.data.ApodsRepository

class ApodsInteractorImpl(private val apodsRepository: ApodsRepository, private val apodsDataToDomainMapper: ApodsDataToDomainMapper): ApodsInteractor {
    override suspend fun fetchApods(): ApodsDomain {
        val data = apodsRepository.fetchPictures()

        // Return domain entities
        return data.map(apodsDataToDomainMapper)
    }
}