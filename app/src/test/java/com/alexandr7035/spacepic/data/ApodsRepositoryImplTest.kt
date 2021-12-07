package com.alexandr7035.spacepic.data

import com.alexandr7035.spacepic.data.remote.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.net.UnknownHostException

class ApodsRepositoryImplTest {

    private val exception = UnknownHostException()

    @Test
    fun test_no_internet() = runBlocking {

        // TODO use test implementation
        val mapper = ApodRemoteToDataMapperImpl()
        val listMapper = ApodListRemoteToDataMapperImpl(mapper)
        val testDataSource = TestCloudDataSource(returnSuccess = false)

        val repository = ApodsRepositoryImpl(
            apodsCloudDataSource = testDataSource,
            apodsListRemoteToDataMapper = listMapper,
            apodRemoteToDataMapper = mapper
        )

        val actual = repository.fetchPictures("date1", "date2")
        val expected = ApodsData.Fail(exception)

        assertEquals(expected, actual)
    }


    inner class TestCloudDataSource(private val returnSuccess: Boolean) : ApodsCloudDataSource {
        override suspend fun fetchApods(startDate: String, endDate: String): List<ApodRemote> {
            if (returnSuccess) {
                return listOf(
                    ApodRemote(
                        apodUrl = "url",
                        date = "2020-12-11",
                        title = "title",
                        description = "Lorem ipsum",
                        mediaType = "image",
                        thumbUrl = "thumb_url"
                    ),

                    ApodRemote(
                        apodUrl = "url2",
                        date = "2020-09-07",
                        title = "title2",
                        description = "Description",
                        mediaType = "video",
                        thumbUrl = null
                    )
                )
            } else {
                throw exception
            }
        }

        override suspend fun fetchApod(date: String): ApodRemote {
            if (returnSuccess) {
                return ApodRemote(
                    apodUrl = "url",
                    date = "2020-12-11",
                    title = "title",
                    description = "Lorem ipsum",
                    mediaType = "image",
                    thumbUrl = "thumb_url"
                )
            }
            else {
                throw exception
            }
        }
    }
}