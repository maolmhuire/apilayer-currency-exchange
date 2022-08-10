package com.maolmhuire.kevin.core.apiservice

import com.maolmhuire.kevin.core.model.AvailableCurrencyResponse
import com.maolmhuire.kevin.core.model.CurrencyConversionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyExchangeService {

    @GET("/exchangerates_data/symbols")
    suspend fun getCurrencies(): Response<AvailableCurrencyResponse>

    @GET("/exchangerates_data/convert")
    suspend fun convertCurrencyValue(
        @Query("amount") amount: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("date") date: String? = null
    ): Response<CurrencyConversionResponse>

}