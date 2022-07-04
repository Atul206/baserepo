package com.roadster.roam.basesetup.ui.main.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.roadster.roam.basesetup.R

abstract class BaseFullScreenDialogFragment : BaseDialogFragment() {

    @get:LayoutRes
    abstract val layout: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        dialog?.let {
//            val window = it.window
//            if (window != null && isAnimated) {
//                window.attributes.windowAnimations = R.style.DialogFragmentAnimation
//            }
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(layout, container, false)

    protected open fun changeStatusBarColor() {
        dialog?.window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor =
                ContextCompat.getColor(requireActivity(), R.color.black)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    abstract fun setupViews()
}