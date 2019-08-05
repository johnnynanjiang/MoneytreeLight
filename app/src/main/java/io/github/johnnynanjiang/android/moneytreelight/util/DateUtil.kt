package io.github.johnnynanjiang.android.moneytreelight.util

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        private const val DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"

        fun getMonthAndYear(dateString: String): String {
            val dateFormat = SimpleDateFormat(DATETIME_PATTERN)
            val calendar = Calendar.getInstance()
            calendar.time = dateFormat.parse(dateString)
            return DateFormatSymbols().months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR)
        }
    }
}