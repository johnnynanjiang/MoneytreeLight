package io.github.johnnynanjiang.android.moneytreelight.util

import junit.framework.Assert.assertEquals
import org.junit.Test

class DateUtilTest {
    @Test
    fun getMonthAndYear() {
        assertEquals("May 2017", DateUtil.getMonthAndYear("2017-05-26T00:00:00+09:00"))
    }
}
