package com.roadster.roam.basesetup.extensions.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeData(lifecycleOwner: LifecycleOwner, action: (data: T) -> Unit) {
    observe(lifecycleOwner, Observer { action(it) })
}