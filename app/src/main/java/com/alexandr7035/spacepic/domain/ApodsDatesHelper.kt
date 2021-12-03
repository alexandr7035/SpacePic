package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.getApodStringDateFromUnix

// API has no pagination by default
// But we can pass startDate and endDate values to fetch apods from the period
// For example, startDate: "2020-11-19", endDate: "2020-11-27"
// NOTE: Maximum apods per request - 100
interface ApodsDatesHelper {
    // Pass System.currentTimeMills() (current day) for first request
    fun getStartDate(lastApodDate: Long): String

    fun getEndDate(lastApodDate: Long): String

    class Impl: ApodsDatesHelper {

        private val apodsPageCount = 25
        private val dailyMills = 24L * 60 * 60 * 1000

        override fun getStartDate(lastApodDate: Long): String {
            val difference = (apodsPageCount-1) * dailyMills
            val statDateUnix = (lastApodDate - difference)
            return statDateUnix.getApodStringDateFromUnix()
        }

        override fun getEndDate(lastApodDate: Long): String {
            // Get previous day before lastApodDate
            val endDateUnix = lastApodDate - dailyMills
            return endDateUnix.getApodStringDateFromUnix()
        }
    }
}