package com.roadster.roam.basesetup.extensions.ui

import android.os.Build
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.M)
fun TextView.setTextColorId(@ColorRes colorIdRes: Int) {
    setTextColor(context.getColor(colorIdRes))
}