package com.maolmhuire.kevin.core.mapper

import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.Currency
import com.maolmhuire.kevin.core.entity.ExchangeFee
import com.maolmhuire.kevin.core.model.AvailableCurrencyResponse
import com.maolmhuire.kevin.core.model.CurrencyConversionResponse

fun AvailableCurrencyResponse.toCurrencyList(): List<Currency> {
    return symbols.map { Currency(it.key, it.value) }
}

fun CurrencyConversionResponse.toExchange(): Exchange {
    return Exchange(
        amount = query.amount,
        rate = info.rate,
        timestamp = info.timestamp,
        result = result,
        from = query.from,
        to = query.to
    )
}

