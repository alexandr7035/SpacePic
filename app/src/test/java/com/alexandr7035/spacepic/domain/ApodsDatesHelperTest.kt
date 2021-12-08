package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.debug
import com.alexandr7035.spacepic.core.extensions.getApodStringDateFromUnix
import org.junit.Assert.*
import org.junit.Test
import timber.log.Timber

// API pagination has following idea: get from startDate: 2020-11-10 to endDate: 2020-11-12
// passed as query parameters
// So we must convert unix dates in the proper format
// Test dates helper here
class ApodsDatesHelperTest {
    // List of only 2 apods
    private val helper = ApodsDatesHelper.Impl(pageSize = 2)

    @Test
    fun test_dates_first_page() {
        val expectedEndDate = System.currentTimeMillis().getApodStringDateFromUnix()
        val actualEndDate = helper.getEndDate(null)
        assertEquals(expectedEndDate, actualEndDate)

        // As apods count in page is 2, get the previous day
        val expectedStartDate = (System.currentTimeMillis() - 86400000).getApodStringDateFromUnix()
        val actualStartDate = helper.getStartDate(null)
        assertEquals(expectedStartDate, actualStartDate)
    }

    @Test
    fun test_dates_pagination() {

        // Assume that last apod date in previous page is current day
        val lastApodDateUnix = System.currentTimeMillis()
        // One day in mills
        val oneDay = 86400000
        val yesterday = lastApodDateUnix - oneDay

        // We expect yesterday here
        val expectedEndDate = yesterday.getApodStringDateFromUnix()
        val actualEndDate = helper.getEndDate(lastApodDateUnix)
        assertEquals(expectedEndDate, actualEndDate)

         // As apods count in page is 2, we expect day before yesterday here
        val expectedStartDate = (yesterday - oneDay).getApodStringDateFromUnix()
        val actualStartDate = helper.getStartDate(lastApodDateUnix)
        assertEquals(expectedStartDate, actualStartDate)
    }
}