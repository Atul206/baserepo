package com.roadster.roam.basesetup.utils

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.roadster.roam.basesetup.R
import com.roadster.roam.basesetup.extensions.UNDEFINED_INT

fun CharSequence.applyClickSpan(
    givenString: String?,
    action: () -> Unit,
    source: SpannableString = SpannableString(this)
): CharSequence {
    givenString?.let {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                action()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = true
            }
        }

        val startIndex = source.indexOf(givenString, ignoreCase = true)
        val endIndex = startIndex + givenString.length
        source.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    return source
}

fun CharSequence.applyFontSpan(
    context: Context,
    @FontRes font: Int,
    givenString: String?,
    source: SpannableString = SpannableString(this)
): CharSequence {
    givenString?.let {
        val typeface = ResourcesCompat.getFont(context, font)
        val startIndex = source.indexOf(givenString, ignoreCase = true)
        val endIndex = startIndex + givenString.length
        source.setSpan(
            CustomTypefaceSpan(typeface),
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return source
}

fun CharSequence.applyColorSpan(
    context: Context,
    @ColorRes color: Int,
    givenString: String?,
    source: SpannableString = SpannableString(this)
): CharSequence {
    givenString?.let {
        val startIndex = source.indexOf(givenString, ignoreCase = true)
        val endIndex = startIndex + givenString.length
        source.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    return source
}

class CustomTypefaceSpan(private val newType: Typeface?) : TypefaceSpan("") {

    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType)
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface?) {
        tf?.let {
            val oldStyle: Int
            val old = paint.typeface
            oldStyle = old?.style ?: 0

            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }

            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }

            paint.typeface = tf
        }
    }
}