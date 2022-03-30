package com.andrew.newsreader.util

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by andrew.liu on 2022/1/19.
 */
object TimeUtils {
    // Use device default TimeZone
    val DEFAULT_TIME_ZONE = TimeZone.getDefault()
    val DateTimeFormatWithHourMinute = SimpleDateFormat("MMM dd, yyyy HH:mm a", Locale.US)
    var HOUR_TIME_MILLS = 60 * 60 * 1000L
    var DATE_TIME_MILLS = 24 * 60 * 60 * 1000L

    fun getDateTimeFormatWithHourMinute(now: Long): String {
        DateTimeFormatWithHourMinute.timeZone = DEFAULT_TIME_ZONE
        return DateTimeFormatWithHourMinute.format(now)
    }

}