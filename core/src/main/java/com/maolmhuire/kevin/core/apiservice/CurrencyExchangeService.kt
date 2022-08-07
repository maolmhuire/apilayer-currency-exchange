package com.maolmhuire.kevin.core.apiservice

import com.maolmhuire.kevin.core.model.AvailableCurrencyResponse
import com.maolmhuire.kevin.core.model.CurrencyConversionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyExchangeService {

    @GET("/symbols")
    suspend fun getCurrencies(): Response<AvailableCurrencyResponse>

    @GET("/convert")
    suspend fun convertCurrencyValue(
        @Query("amount") page: String,
        @Query("from") limit: String,
        @Query("to") sort: String,
        @Query("date") imgOnly: String? = null
    ): Response<CurrencyConversionResponse>

}