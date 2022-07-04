package com.roadster.roam.basesetup.utils

import android.content.Context
import com.roadster.roam.basesetup.R
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

object AmountFormatter {
    const val ZERO_AMOUNT = "0.000"
    private const val CURRENCY_CODE_LENGTH = 3
    private const val DEFAULT_PLACES = 0
    private const val COINS_PLACES = 2
    private const val BHD_COINS_PLACES = 3
    const val BHD_CURRENCY_CODE = "BHD"

    fun BigDecimal.formatAmount(currencyCode: String = BHD_CURRENCY_CODE) =
        format(this, currencyCode = currencyCode)

    fun String?.formatAmount(currencyCode: String? = BHD_CURRENCY_CODE): String {
        val prefix = if (currencyCode.isNullOrBlank()) "" else "$currencyCode "
        return try {
            "$prefix${BigDecimal(this ?: ZERO_AMOUNT).formatAmount()}"
        } catch (e: Exception) {
            "$prefix$ZERO_AMOUNT"
        }
    }

    fun String?.formatAmountWithRounding(
        currencyCode: String? = BHD_CURRENCY_CODE,
        places: Int = BHD_COINS_PLACES
    ): String {
        val prefix = if (currencyCode.isNullOrBlank()) "" else "$currencyCode "
        return try {
            "$prefix${BigDecimal(this ?: ZERO_AMOUNT).formatAmountWithRounding(places = places)}"
        } catch (e: Exception) {
            "$prefix$ZERO_AMOUNT"
        }
    }

    fun BigDecimal.formatAmountWithRounding(
        currencyCode: String = BHD_CURRENCY_CODE,
        places: Int = BHD_COINS_PLACES
    ) =
        format(this, currencyCode = currencyCode, coinsPlaces = places)

    fun getSmartAmount(coins: String?, currencyCode: String? = BHD_CURRENCY_CODE) =
        try {
            var amount =
                "${currencyCode ?: ""} ${BigDecimal(coins ?: ZERO_AMOUNT).formatAmount()}".trim()
            while ((amount.last() == '0' || amount.last() == '.' || amount.last() == ',') && amount.length > 1 &&
                (amount.contains('.') || amount.contains(','))
            ) {
                amount = amount.substring(0, amount.length - 1)
            }
            amount
        } catch (e: Exception) {
            ""
        }

    fun getFormattedAmountWithSign(
        context: Context,
        currencyCode: String? = BHD_CURRENCY_CODE,
        coins: String?,
        amountType:String?,
        useColor: Boolean = true
    ): CharSequence {
        val amount = BigDecimal(coins ?: "0")
        val isDebit = amountType == "Debit"
        val nonNullCurrency = currencyCode ?: BHD_CURRENCY_CODE
        val formatted =
            "$nonNullCurrency ${if (isDebit) "-" else ""}${
                amount.abs().formatAmount(nonNullCurrency)
            }"
        return if (useColor) {
            formatted.applyColorSpan(
                context,
                if (isDebit) R.color.money_expense_color else R.color.money_income_color,
                formatted
            )
        } else
            formatted
    }

    fun getFormattedAmountWithOutSign(
        context: Context,
        currencyCode: String? = "",
        coins: String?,
        useColor: Boolean = false
    ): CharSequence {
        try {
            val amount = BigDecimal(coins ?: "0")
            val isDebit = amount.signum() > 0
            val nonNullCurrency = currencyCode ?: ""
            val sign = getSign(isDebit)
            val formatted =
                "$nonNullCurrency  $sign${
                    amount.abs().formatAmount(nonNullCurrency)
                }"
            return if (useColor) {
                formatted.applyColorSpan(
                    context,
                    if (isDebit) R.color.money_income_color else R.color.money_expense_color,
                    formatted
                )
            } else
                formatted
        } catch (e: Exception) {
            val nonNullCurrency = ""
            return "$nonNullCurrency ${
                "0.0".formatAmount(nonNullCurrency)
            }"
        }
    }

    private fun getSign(sign:Boolean):String {
        if(!sign) return "-"
        else return ""
    }

    private fun format(
        value: BigDecimal,
        useGrouping: Boolean = true,
        currencyCode: String,
        coinsPlaces: Int? = null
    ): String {
        val fractionDigits = when {
            coinsPlaces != null -> coinsPlaces
            currencyCode == BHD_CURRENCY_CODE -> BHD_COINS_PLACES
            else -> COINS_PLACES
        }
        return NumberFormat.getNumberInstance(Locale.UK).apply {
            isGroupingUsed = useGrouping
            maximumFractionDigits = fractionDigits
            minimumFractionDigits = fractionDigits
        }.format(value)
    }
}