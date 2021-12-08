package com.alexandr7035.spacepic.domain

import com.alexandr7035.spacepic.core.extensions.getApodStringDateFromUnix

// API has no pagination by default
// But we can pass startDate and endDate values to fetch apods from the period
// For example, startDate: "2020-11-19", endDate: "2020-11-27"
// NOTE: Maximum apods per request - 100
interface ApodsDatesHelper {
    // Pass System.currentTimeMills() (current day) for first request
    fun getStartDate(lastApodDate: Long?): String

    fun getEndDate(lastApodDate: Long?): String

    class Impl(private val pageSize: Int): ApodsDatesHelper {
        private val dailyMills = 24L * 60 * 60 * 1000

        override fun getStartDate(lastApodDate: Long?): String {

            val startDateUnix: Long = if (lastApodDate == null) {
                val difference = (pageSize-1) * dailyMills
                System.currentTimeMillis() - difference
            }
            else {
                val difference = pageSize * dailyMills
                lastApodDate - difference
            }

            return startDateUnix.getApodStringDateFromUnix()
        }

        // Pass last apod date from previous page
        override fun getEndDate(lastApodDate: Long?): String {

            val endDateUnix: Long = if (lastApodDate == null) {
                System.currentTimeMillis()
            } else {
                // Get previous day before lastApodDate
                lastApodDate - dailyMills
            }

            return endDateUnix.getApodStringDateFromUnix()
        }
    }
}