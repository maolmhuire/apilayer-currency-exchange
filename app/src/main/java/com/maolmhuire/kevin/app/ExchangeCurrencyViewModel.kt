package com.maolmhuire.kevin.app

import android.text.format.DateUtils
import androidx.lifecycle.*
import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.entity.*
import com.maolmhuire.kevin.core.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExchangeCurrencyViewModel @Inject constructor(
    private val availableUseCase: GetAvailableCurrenciesUseCase,
    private val exchangeUseCase: ExchangeCurrencyUseCase,
    private val exchangeFeeUseCase: GetCurrencyExchangeFeeUseCase,
    private val deductFeeUseCase: DeductExchangeFromBalanceUseCase,
    private val insertExchangeHistoryUseCase: InsertExchangeHistoryUseCase,
    userDataUseCase: GetLocalUserFlowUseCase
) : ViewModel() {

    private val _currencies: MutableLiveData<ResultState<List<Currency>>> =
        MutableLiveData<ResultState<List<Currency>>>()
    val currencies: LiveData<ResultState<List<Currency>>> = _currencies

    val userData: LiveData<UserDetails?> = userDataUseCase.invoke().asLiveData()

    private val _exchange: MutableLiveData<ResultState<Exchange>> =
        MutableLiveData<ResultState<Exchange>>()
    val exchange: LiveData<ResultState<Exchange>> = _exchange

    private val refreshCurrenciesJob: Job = refreshCurrencies()

    init {
        getCurrencies()
    }

    fun retryCurrencies() {
        getCurrencies()
    }

    private fun getCurrencies() {
        _currencies.postValue(ResultState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            availableUseCase().run {
                _currencies.postValue(this)
            }
        }
    }

    private fun refreshCurrencies(): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                delay(60 * 1000)
                availableUseCase().run {
                    if (this is ResultState.Success) {
                        _currencies.postValue(this)
                    }
                }
                Timber.d("Refresh Job")
            }
        }
    }

    fun exchangeCurrency(
        amount: String,
        from: String,
        to: String,
        date: String? = null
    ) {
        _exchange.postValue(ResultState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            with(exchangeUseCase(amount, from, to, date)) {
                if (this is ResultState.Success) {
                    getExchangeFee(this.data)
                } else {
                    _exchange.postValue(this)
                }
            }
        }
    }

    private suspend fun getExchangeFee(data: Exchange) {
        with(exchangeFeeUseCase(data) { DateUtils.isToday(it) }) {
            if (this is ResultState.Success) {
                deductFeeFromBalance(this.data)
            } else {
                _exchange.postValue(this)
            }
        }
    }

    private suspend fun deductFeeFromBalance(data: Exchange) {
        with(deductFeeUseCase(data)) {
            if (this is ResultState.Success) {
                insertExchangeHistoryUseCase(this.data)
            }
            _exchange.postValue(this)
        }
    }

    override fun onCleared() {
        super.onCleared()
        refreshCurrenciesJob.cancel()
    }
}