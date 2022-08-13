package com.maolmhuire.kevin.core.repo

import com.maolmhuire.kevin.core.apiservice.CurrencyExchangeService
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.Currency
import com.maolmhuire.kevin.core.entity.ExchangeFee
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.mapper.toExchange
import com.maolmhuire.kevin.core.mapper.toCurrencyList

interface CurrencyRepo {

    suspend fun getCurrencies(): ResultState<List<Currency>>

    suspend fun exchangeCurrencyValue(
        amount: String,
        from: String,
        to: String,
        date: String?
    ): ResultState<Exchange>

    suspend fun getExchangeEuroFeeRemote(exchangeToCurCode: String): ResultState<Double>
}

class CurrencyRepoImpl(private val service: CurrencyExchangeService): CurrencyRepo {

    override suspend fun getCurrencies(): ResultState<List<Currency>> {
        return try {
            ResultState.Success(
                requireNotNull(service.getCurrencies().body()).toCurrencyList()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResultState.Error(e)
        }
    }

    override suspend fun exchangeCurrencyValue(
        amount: String,
        from: String,
        to: String,
        date: String?
    ): ResultState<Exchange> {
        return try {
            ResultState.Success(
                requireNotNull(
                    service.exchangeCurrencyValue(
                        amount, from, to, date
                    ).body()
                ).toExchange()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResultState.Error(e)
        }
    }

    override suspend fun getExchangeEuroFeeRemote(exchangeToCurCode: String): ResultState<Double> {
        return try {
            ResultState.Success(
                requireNotNull(
                    service.exchangeCurrencyValue(
                        "0.30", exchangeToCurCode, "EUR"
                    ).body()
                ).result
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResultState.Error(e)
        }
    }
}