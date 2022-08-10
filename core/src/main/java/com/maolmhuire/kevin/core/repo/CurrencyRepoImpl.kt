package com.maolmhuire.kevin.core.repo

import com.maolmhuire.kevin.core.datasource.CurrencyDataSource
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.Currency
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
}

class CurrencyRepoImpl(private val dataSource: CurrencyDataSource): CurrencyRepo {

    override suspend fun getCurrencies(): ResultState<List<Currency>> {
        return try {
            ResultState.Success(
                requireNotNull(dataSource.getCurrencies().body()).toCurrencyList()
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
                    dataSource.exchangeCurrencyValue(
                        amount, from, to, date
                    ).body()
                ).toExchange()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            ResultState.Error(e)
        }
    }
}