package com.roadster.roam.basesetup.extensions.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun View.toGone() {
    isVisible = false
}

fun View.toVisible(isVisible: Boolean = true) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun View.setOnClickListenerWithDebounce(action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (System.currentTimeMillis() - lastClickTime < 1000L) return
            else action()
            lastClickTime = System.currentTimeMillis()
        }
    })
}



fun <T : ViewDataBinding> ViewGroup.dataBind(@LayoutRes layoutRes: Int): T =
    DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, this, false)