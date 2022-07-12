package com.roadster.roam.basesetup.ui.main.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.extensions.UNDEFINED_INT
import com.roadster.roam.basesetup.extensions.isUndefined

open class BaseAlertDialogFragment : DialogFragment(),
    DialogInterface.OnClickListener {

    protected open val title: String? = null

    @StringRes
    protected open val titleId = UNDEFINED_INT

    protected open val message: String? = null

    @StringRes
    protected open val messageId = UNDEFINED_INT

    protected open val positiveButtonText: String? = null

    @StringRes
    protected open val positiveButtonTextId = UNDEFINED_INT

    protected open val negativeButtonText: String? = null

    @StringRes
    protected open val negativeButtonTextId = UNDEFINED_INT

    protected open val neutralButtonText: String? = null

    @StringRes
    protected open val neutralButtonTextId = UNDEFINED_INT

    @StyleRes
    protected open val styleResId = R.style.DefaultAlertDialogStyle

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        with(AlertDialog.Builder(requireContext(), styleResId)) {
            if (!titleId.isUndefined()) setTitle(titleId)
            else setTitle(title)

            if (!messageId.isUndefined()) setMessage(messageId)
            else setMessage(message)

            if (!positiveButtonTextId.isUndefined()) {
                setPositiveButton(positiveButtonTextId, this@BaseAlertDialogFragment)
            } else if (positiveButtonText != null) {
                setPositiveButton(positiveButtonText, this@BaseAlertDialogFragment)
            }

            if (!negativeButtonTextId.isUndefined()) {
                setNegativeButton(negativeButtonTextId, this@BaseAlertDialogFragment)
            } else if (negativeButtonText != null) {
                setNegativeButton(negativeButtonText, this@BaseAlertDialogFragment)
            }

            if (!neutralButtonTextId.isUndefined()) {
                setNeutralButton(neutralButtonTextId, this@BaseAlertDialogFragment)
            } else if (neutralButtonText != null) {
                setNeutralButton(neutralButtonTextId, this@BaseAlertDialogFragment)
            }

            create()
        }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> onPositiveButtonClick()
            DialogInterface.BUTTON_NEGATIVE -> onNegativeButtonClick()
            DialogInterface.BUTTON_NEUTRAL -> onNeutralButtonClick()
        }
    }

    protected open fun onPositiveButtonClick() {
        dismiss()
    }

    protected open fun onNegativeButtonClick() {
        dismiss()
    }

    protected open fun onNeutralButtonClick() {
        dismiss()
    }
}