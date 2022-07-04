package com.pfm.sdk.core.utils

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun String?.getStringSafe(context: Context, @StringRes defaultTextId: Int): String =
    if (this.isNullOrEmpty()) {
        context.getString(defaultTextId)
    } else this

@ColorInt
fun String?.getColorSafe(context: Context, @ColorRes defaultColorId: Int): Int =
    if (this.isNullOrEmpty()) {
        ContextCompat.getColor(context, defaultColorId)
    } else Color.parseColor(this)