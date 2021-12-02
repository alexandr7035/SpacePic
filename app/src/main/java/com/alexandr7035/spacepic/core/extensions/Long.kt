package com.alexandr7035.spacepic.core.extensions

import java.text.SimpleDateFormat
import java.util.*

// Apod endDate and startDate must be in  "yyyy-MM-dd" format
fun Long.getApodStringDateFromUnix(): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    format.timeZone = TimeZone.getTimeZone("GMT")
    return format.format(this)
}