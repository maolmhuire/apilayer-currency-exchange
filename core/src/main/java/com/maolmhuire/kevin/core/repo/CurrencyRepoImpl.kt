package com.maolmhuire.kevin.core.repo

import com.maolmhuire.kevin.core.datasource.CurrencyDataSource

interface CurrencyRepo {
    suspend fun getCurrencies()
    suspend fun exchangeCurrencyValue(
        page: String,
        limit: String,
        sort: String,
        imgOnly: String?
    )
}

class CurrencyRepoImpl(private val dataSource: CurrencyDataSource): CurrencyRepo {

    override suspend fun getCurrencies() {
        TODO("Not yet implemented")
    }

    override suspend fun exchangeCurrencyValue(
        page: String,
        limit: String,
        sort: String,
        imgOnly: String?
    ) {
        TODO("Not yet implemented")
    }

}