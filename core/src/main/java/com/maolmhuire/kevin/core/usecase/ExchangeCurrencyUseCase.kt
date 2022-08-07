package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.repo.CurrencyRepo
import javax.inject.Inject

class ExchangeCurrencyUseCase @Inject constructor(
    private val currencyRepo: CurrencyRepo
) {
    suspend operator fun invoke(
        page: String,
        limit: String,
        sort: String,
        imgOnly: String? = null
    ) = currencyRepo.exchangeCurrencyValue(page, limit, sort, imgOnly)
}