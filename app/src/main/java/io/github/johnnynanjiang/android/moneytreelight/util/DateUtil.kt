package io.github.johnnynanjiang.android.moneytreelight.util

import java.lang.IllegalArgumentException
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

        fun getDayAsString(dateString: String): String {
            val date = getDateFromString(DATETIME_PATTERN, dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date
            return getDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH))
        }

        private fun getDayOfMonth(n: Int): String {
            assert(n in 1..31) { throw IllegalArgumentException("illegal day of month: $n") }

            if (n in 11..13) {
                return "th"
            }

            val suffix = when (n % 10) {
                1 -> "st"
                2 -> "nd"
                3 -> "rd"
                else -> "th"
            }

            return "$n$suffix"
        }

        private fun getDateFromString(pattern: String, dateString: String): Date =
            SimpleDateFormat(pattern, Locale.JAPAN).parse(dateString)
    }
}