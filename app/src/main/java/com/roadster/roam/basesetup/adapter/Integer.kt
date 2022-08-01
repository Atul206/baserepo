package com.roadster.roam.basesetup.adapter

import android.content.res.Resources
import android.util.DisplayMetrics

const val UNDEFINED_INT=-1
fun Int.toPx(): Int {
    val metrics = Resources.getSystem().displayMetrics

    return (this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}