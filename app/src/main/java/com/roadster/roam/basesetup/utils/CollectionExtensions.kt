package com.roadster.roam.basesetup.utils

import java.math.BigDecimal

inline fun <T, R> MutableCollection<R>.mapAndReplace(list: Collection<T>?, map: (T) -> R) {
    val mapped = list?.map { map(it) } ?: emptyList()
    clear()
    addAll(mapped)
}

inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum = BigDecimal(0)
    for (element in this) {
        sum = sum.add(selector(element))
    }
    return sum
}