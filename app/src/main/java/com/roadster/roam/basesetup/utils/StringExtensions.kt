package com.roadster.roam.basesetup.utils

import java.math.BigDecimal

fun String?.isZeroOrBlank(): Boolean {
    return when {
        this.isNullOrBlank() -> true
        else -> try {
            (BigDecimal(this).compareTo(BigDecimal.ZERO) == 0)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.capitalize() }
