package com.kaycode.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateUtils @Inject constructor(): IDateUtils {
    override fun getCurrentDateInYyyymmdd(): String =
        LocalDate.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD))


    override fun getThreeMonthsAgoDateInYyyymmdd(): String =
        LocalDate.now().minusMonths(THREE_MONTHS_AGO)
            .format(DateTimeFormatter.ofPattern(YYYY_MM_DD))

    companion object {
        private const val YYYY_MM_DD = "yyyy-MM-dd"
        private const val THREE_MONTHS_AGO = 3L
    }
}
