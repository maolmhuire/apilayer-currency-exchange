package com.maolmhuire.kevin.app

import androidx.lifecycle.ViewModel
import com.maolmhuire.kevin.core.usecase.ExchangeCurrencyUseCase
import com.maolmhuire.kevin.core.usecase.GetAvailableCurrenciesUseCase
import com.maolmhuire.kevin.core.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeCurrencyViewModel @Inject constructor(
    private val exchangeUseCase: ExchangeCurrencyUseCase,
    private val availableUseCase: GetAvailableCurrenciesUseCase,
    private val userDataUseCase: GetUserDataUseCase
) : ViewModel() {

}