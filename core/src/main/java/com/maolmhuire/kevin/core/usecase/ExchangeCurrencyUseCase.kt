package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.repo.CurrencyRepo
import javax.inject.Inject

class ExchangeCurrencyUseCase @Inject constructor(
    private val currencyRepo: CurrencyRepo
) {
    suspend operator fun invoke(
        amount: String,
        from: String,
        to: String,
        date: String? = null
    ) = currencyRepo.exchangeCurrencyValue(amount, from, to, date)
}