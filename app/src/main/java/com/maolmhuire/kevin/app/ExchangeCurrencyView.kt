package com.maolmhuire.kevin.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.math.MathUtils
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.entity.User
import com.maolmhuire.kevin.core.exception.NegativeBalanceException
import com.maolmhuire.kevin.core.exception.NullBalanceException
import com.maolmhuire.kevin.core.exception.NullFeeException
import timber.log.Timber

@Composable
fun ExchangeCurrencyView(viewmodel: ExchangeCurrencyViewModel) {
    Column {
        with(viewmodel) {

            val user = userData.observeAsState().value

            if (user == null) {
                CircularProgressLoader()
            } else {
                ExchangeHeader(requireNotNull(user.user), user.balances)
            }

            Divider()
            Spacer(modifier = Modifier.height(40.dp))

            val currencies = currencies.observeAsState().value
            val exchange = exchange.observeAsState().value
            if (user != null && currencies is ResultState.Success) {
                ExchangeSubtitle()
                Spacer(modifier = Modifier.height(20.dp))
                ExchangeForm(
                    exchange,
                    user.balances.map { requireNotNull(it.code) },
                    currencies.data.map { it.code }
                ) { from, to, amount ->
                    exchangeCurrency(amount, from, to)
                }
            } else if (currencies is ResultState.Loading) {
                CircularProgressLoader()
            } else if (currencies is ResultState.Error) {
                CurrencyLoadFailure { retryCurrencies() }
            }

            when (exchange) {
                is ResultState.Success -> {
                    with(exchange.data) {
                        val fee = displayFee()
                        ExchangeSuccessDialog(
                            body = stringResource(
                                R.string.exchange_success_message,
                                amount.toCurrencyDisplayValue(from),
                                result.toCurrencyDisplayValue(to),
                                fee
                            )
                        )
                    }
                }
                is ResultState.Loading -> {
                    CircularProgressLoader()
                }
                is ResultState.Error -> {
                    when (exchange.exception) {
                        is NegativeBalanceException -> NegativeBalanceErrorDialog()
                        is NullFeeException, is NullBalanceException -> UnexpectedErrorDialog()
                        else -> NetworkErrorDialog()
                    }
                }
                else -> { /* no-op */}
            }
        }
    }
}

@Composable
fun CircularProgressLoader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.secondaryVariant
        )
    }
}

private fun Exchange.displayFee(): String {
    val fromNormalised = fee?.fromValueFee ?: 0.0
    val toNormalised = fee?.toValueFee ?: 0.0
    return if (toNormalised > fromNormalised) {
        toNormalised.toCurrencyDisplayValue(to)
    } else {
        fromNormalised.toCurrencyDisplayValue(from)
    }
}
