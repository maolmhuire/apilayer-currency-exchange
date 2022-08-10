package com.maolmhuire.kevin.core.mapper

import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.Currency
import com.maolmhuire.kevin.core.model.AvailableCurrencyResponse
import com.maolmhuire.kevin.core.model.CurrencyConversionResponse

fun AvailableCurrencyResponse.toCurrencyList(): List<Currency> {
    return symbols.map { Currency(it.key, it.value) }
}

fun CurrencyConversionResponse.toExchange(): Exchange {
    return Exchange(
        amount = query.amount,
        from = query.from,
        to = query.to,
        rate = info.rate,
        timestamp = info.timestamp
    )
}

