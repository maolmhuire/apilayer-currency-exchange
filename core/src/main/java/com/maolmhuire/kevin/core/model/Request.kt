package com.maolmhuire.kevin.core.model

data class AvailableCurrencyResponse(
    val success: Boolean,
    val symbols: HashMap<String, String>?
)

data class CurrencyConversionResponse(
    val date: String,
    val historical: String,
    val info: CurrencyConversionInfo,
    val query: CurrencyConversionQuery,
    val result: Double,
    val success: Boolean
)

data class CurrencyConversionInfo(
    val rate: Double,
    val timestamp: Long
)

data class CurrencyConversionQuery(
    val amount: Double,
    val from: String,
    val to: String,
)