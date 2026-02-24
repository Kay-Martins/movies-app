package com.kaycode.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

class DateUtils @Inject constructor(): IDateUtils {
    override fun getCurrentDateInYyyymmdd(): String =
        LocalDate.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD))


    override fun getThreeMonthsAgoDateInYyyymmdd(): String =
        LocalDate.now().minusMonths(THREE_MONTHS_AGO)
            .format(DateTimeFormatter.ofPattern(YYYY_MM_DD))

    override fun getReleaseYearFromDateString(releaseDate: String): Int {
        try {
            val date = LocalDate.parse(releaseDate)
            return date.year
        } catch (e: DateTimeParseException) {
            return 0
        }
    }

    companion object {
        private const val YYYY_MM_DD = "yyyy-MM-dd"
        private const val THREE_MONTHS_AGO = 3L
    }
}
