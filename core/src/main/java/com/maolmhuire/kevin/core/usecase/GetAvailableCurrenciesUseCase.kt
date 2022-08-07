package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.repo.CurrencyRepo
import javax.inject.Inject

class GetAvailableCurrenciesUseCase @Inject constructor(
    private val currencyRepo: CurrencyRepo
) {
    suspend operator fun invoke() = currencyRepo.getCurrencies()
}