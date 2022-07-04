package com.roadster.roam.basesetup.utils

import java.util.*

val Calendar.year
    get() = get(Calendar.YEAR)

val Calendar.month
    get() = get(Calendar.MONTH)

val Calendar.dayOfMonth
    get() = get(Calendar.DAY_OF_MONTH)

fun Calendar.toDayStart() = apply {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
}

fun Calendar.toDayEnd() = apply {
    set(Calendar.HOUR_OF_DAY, 23)
    set(Calendar.MINUTE, 59)
    set(Calendar.SECOND, 59)
    set(Calendar.MILLISECOND, 999)
}