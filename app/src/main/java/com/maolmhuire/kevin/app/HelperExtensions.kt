package com.maolmhuire.kevin.app

import java.text.NumberFormat
import java.util.*

fun String.toCurrencyDisplayName(): String {
    return Currency.getInstance(this).displayName
}

fun Double.toCurrencyDisplayValue(currencyCode: String): String =
    with(NumberFormat.getCurrencyInstance()) {
        currency = Currency.getInstance(currencyCode)
        return format(this@toCurrencyDisplayValue)
}