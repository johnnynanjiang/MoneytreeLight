package io.github.johnnynanjiang.android.moneytreelight.util

import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {
        private const val DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss"
        private const val DATETIME_YEAR_MONTH_ONLY_PATTERN = "yyyy-MM"

        fun getMonthAndYearAsString(dateString: String): String =
            getMonthAndYearAsString(getDateFromString(DATETIME_PATTERN, dateString))

        fun getDateWithYearAndMonthOnly(dateString: String): Date =
            getDateFromString(DATETIME_YEAR_MONTH_ONLY_PATTERN, dateString)

        fun getMonthAndYearAsString(date: Date): String {
            val calendar = Calendar.getInstance()
            calendar.time = date
            return "${DateFormatSymbols().months[calendar.get(Calendar.MONTH)]} ${calendar.get(Calendar.YEAR)}"
        }

        private fun getDateFromString(pattern: String, dateString: String): Date =
            SimpleDateFormat(pattern).parse(dateString)
    }
}