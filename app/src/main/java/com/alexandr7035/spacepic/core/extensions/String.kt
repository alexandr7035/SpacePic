package com.alexandr7035.spacepic.core.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.getApodUnixDateFromString(): Long {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    format.timeZone = TimeZone.getTimeZone("GMT")
    return format.parse(this)!!.time
}

fun Long.getStringDateFromUnix(
    stringFormat: String,
    timezoneStr: String? = null,
    locale: Locale = Locale.US
): String {
    val format = SimpleDateFormat(stringFormat, locale)

    if (timezoneStr != null) {
        format.timeZone = TimeZone.getTimeZone(timezoneStr)
    }

    return format.format(this)
}