package com.maolmhuire.kevin.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.Currency
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.entity.User
import com.maolmhuire.kevin.core.usecase.ExchangeCurrencyUseCase
import com.maolmhuire.kevin.core.usecase.GetAvailableCurrenciesUseCase
import com.maolmhuire.kevin.core.usecase.GetUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeCurrencyViewModel @Inject constructor(
    private val exchangeUseCase: ExchangeCurrencyUseCase,
    private val availableUseCase: GetAvailableCurrenciesUseCase,
    private val userDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _currencies: MutableLiveData<ResultState<List<Currency>>> =
        MutableLiveData<ResultState<List<Currency>>>()
    val currencies: LiveData<ResultState<List<Currency>>> = _currencies

    private val _userData: MutableLiveData<ResultState<User>> =
        MutableLiveData<ResultState<User>>()
    val userData: LiveData<ResultState<User>> = _userData

    private val _exchange: MutableLiveData<ResultState<Exchange>> =
        MutableLiveData<ResultState<Exchange>>()
    val exchange: LiveData<ResultState<Exchange>> = _exchange

    init {
        getUserData()
        getCurrencies()
    }

    fun getCurrencies() {
        _currencies.postValue(ResultState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            availableUseCase().run {
                _currencies.postValue(this)
            }
        }
    }

    fun getUserData() {
        _userData.postValue(ResultState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            userDataUseCase().run {
                _userData.postValue(this)
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
            exchangeUseCase(amount, from, to, date).run {
                _exchange.postValue(this)
            }
        }
    }

}