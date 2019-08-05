package io.github.johnnynanjiang.android.moneytreelight.util

import org.junit.Assert.*
import org.junit.Test

class DateUtilTest {
    @Test
    fun getMonthAndYearAsString() {
        assertEquals("May 2017", DateUtil.getMonthAndYearAsString("2017-05-26T00:00:00+09:00"))
    }

    @Test
    fun getMonthAndYearAsStringFromDate() {
        assertEquals(
            "May 2017",
            DateUtil.getMonthAndYearAsString(DateUtil.getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
        )
    }

    @Test
    fun getDateWithYearAndMonthOnly() {
        with(DateUtil) {
            assertFalse(getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
            assertFalse(getDateWithYearAndMonthOnly("2017-05-26T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
            assertFalse(getDateWithYearAndMonthOnly("2017-05-27T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))

            assertTrue(getDateWithYearAndMonthOnly("2017-06-01T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
            assertTrue(getDateWithYearAndMonthOnly("2018-05-26T10:00:00+09:00") > getDateWithYearAndMonthOnly("2017-05-26T00:00:00+09:00"))
        }
    }
}
