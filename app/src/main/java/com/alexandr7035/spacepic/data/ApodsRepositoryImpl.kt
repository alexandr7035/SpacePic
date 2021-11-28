package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.data.remote.ApodListRemoteToDataMapper
import javax.inject.Inject

class ApodsRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val apodsListRemoteToDataMapper: ApodListRemoteToDataMapper
) : ApodsRepository {
    override suspend fun fetchPictures(): ApodsData {
        try {
            val remoteList = cloudDataSource.fetchApods()
            val data = apodsListRemoteToDataMapper.map(remoteList)
            return ApodsData.Success(data)

        } catch (e: Exception) {
            return ApodsData.Fail(e)
        }
    }
}