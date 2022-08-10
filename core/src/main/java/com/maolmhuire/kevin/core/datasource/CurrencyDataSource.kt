package com.maolmhuire.kevin.core.datasource

import com.maolmhuire.kevin.core.apiservice.CurrencyExchangeService
import com.maolmhuire.kevin.core.model.AvailableCurrencyResponse
import com.maolmhuire.kevin.core.model.CurrencyConversionResponse
import retrofit2.Response

class CurrencyDataSource(
    private val service: CurrencyExchangeService
) {
    suspend fun getCurrencies(): Response<AvailableCurrencyResponse> =
        service.getCurrencies()

    suspend fun exchangeCurrencyValue(
        amount: String,
        from: String,
        to: String,
        date: String? = null
    ): Response<CurrencyConversionResponse> =
        service.convertCurrencyValue(amount, from, to, date)
}