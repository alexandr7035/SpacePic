package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.core.extensions.getApodStringDateFromUnix
import com.alexandr7035.spacepic.data.ApodsDataToDomainMapper
import com.alexandr7035.spacepic.data.ApodsRepository
import timber.log.Timber
import java.time.Instant
import java.util.*

class ApodsInteractorImpl(
    private val apodsRepository: ApodsRepository,
    private val apodsDataToDomainMapper: ApodsDataToDomainMapper,
    private val apodsDatesHelper: ApodsDatesHelper
): ApodsInteractor {
    override suspend fun fetchApods(lastApodDate: Long): ApodsDomain {

        val startDate = apodsDatesHelper.getStartDate(lastApodDate)
        val endDate = apodsDatesHelper.getEndDate(lastApodDate)

        val data = apodsRepository.fetchPictures(startDate, endDate)

        // Return domain entities
        return data.map(apodsDataToDomainMapper)
    }
}