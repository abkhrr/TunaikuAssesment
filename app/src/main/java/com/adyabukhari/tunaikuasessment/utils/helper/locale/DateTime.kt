package com.adyabukhari.tunaikuasessment.utils.helper.locale

import java.text.SimpleDateFormat
import java.util.*

object DateTime {
    fun convertCalendarToCustomFormatDate(calendar: Calendar): String {
        val newFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val date = calendar.time

        return newFormat.format(date)
    }
}