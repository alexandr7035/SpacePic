package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.core.extensions.getApodStringDateFromUnix
import com.alexandr7035.spacepic.data.ApodsDataToDomainMapper
import com.alexandr7035.spacepic.data.ApodsRepository
import timber.log.Timber
import java.time.Instant
import java.util.*

class ApodsInteractorImpl(private val apodsRepository: ApodsRepository, private val apodsDataToDomainMapper: ApodsDataToDomainMapper): ApodsInteractor {
    override suspend fun fetchApods(lastApodDate: Long): ApodsDomain {

        // Page size
        val apodsCount = 5
        
        val endDateStr = lastApodDate.getApodStringDateFromUnix()

        val difference = (apodsCount-1) * 24L * 60 * 60 * 1000

        val statDateUnix = (System.currentTimeMillis() - difference)
        val startDateStr = statDateUnix.getApodStringDateFromUnix()

        Timber.debug("$startDateStr $endDateStr")

        val data = apodsRepository.fetchPictures(startDateStr, endDateStr)

        // Return domain entities
        return data.map(apodsDataToDomainMapper)
    }
}