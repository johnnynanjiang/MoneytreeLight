package io.github.johnnynanjiang.android.moneytreelight.util

import org.junit.Assert.*
import org.junit.Test

class DateUtilTest {
    @Test
    fun shouldGetMonthAndYearStringFromADateString() {
        assertEquals("May 2017", DateUtil.getMonthAndYearAsString("2017-05-26T00:00:00+09:00"))
    }

    @Test
    fun shouldGetMonthAndYearStringFromADateWithYearAndMonthOnly() {
        assertEquals(
            "May 2017",
            DateUtil.getMonthAndYearAsString(DateUtil.getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
        )
    }

    @Test
    fun shouldGetDateWithYearAndMonthOnlyFromADateString() {
        with(DateUtil) {
            assertFalse(getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
            assertFalse(getDateWithYearAndMonthOnly("2017-05-26T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
            assertFalse(getDateWithYearAndMonthOnly("2017-05-27T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))

            assertTrue(getDateWithYearAndMonthOnly("2017-06-01T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
            assertTrue(getDateWithYearAndMonthOnly("2018-05-26T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
        }
    }

    @Test
    fun shouldGetDayFromADateString() {
        with(DateUtil) {
            assertEquals("1st", getDayAsString("2017-05-01T00:00:00+09:00"))
            assertEquals("2nd", getDayAsString("2017-05-02T00:00:00+09:00"))
            assertEquals("3rd", getDayAsString("2017-05-03T00:00:00+09:00"))
            assertEquals("4th", getDayAsString("2017-05-04T00:00:00+09:00"))
            assertEquals("30th", getDayAsString("2017-05-30T00:00:00+09:00"))
            assertEquals("31st", getDayAsString("2017-05-31T00:00:00+09:00"))
        }
    }
}
