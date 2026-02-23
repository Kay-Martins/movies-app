package com.kaycode.core.utils

import java.time.LocalDate

interface IDateUtils {
    fun getCurrentDateInYyyymmdd(): String
    fun getThreeMonthsAgoDateInYyyymmdd(): String

}