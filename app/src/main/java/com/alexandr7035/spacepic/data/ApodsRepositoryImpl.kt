package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.data.remote.ApodListRemoteToDataMapper
import timber.log.Timber
import javax.inject.Inject

class ApodsRepositoryImpl @Inject constructor(
    private val apodsCloudDataSource: ApodsCloudDataSource,
    private val apodsListRemoteToDataMapper: ApodListRemoteToDataMapper
) : ApodsRepository {
    override suspend fun fetchPictures(startDate: String, endDate: String): ApodsData {
        try {
            val remoteList = apodsCloudDataSource.fetchApods(startDate, endDate)
            val data = apodsListRemoteToDataMapper.map(remoteList)
            return ApodsData.Success(data)

        } catch (e: Exception) {
            Timber.debug("exception on fetching data: $e")
            return ApodsData.Fail(e)
        }
    }
}