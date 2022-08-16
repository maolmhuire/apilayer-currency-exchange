package com.maolmhuire.kevin.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.entity.User
import timber.log.Timber

@Composable
fun ExchangeCurrencyView(viewmodel: ExchangeCurrencyViewModel) {
    Column {
        with(viewmodel) {
            val user = userData.observeAsState().value
            if (user == null) {
                CircularProgressLoader()
            } else {
                ExchangeHeader(user.user!!, user.balances)
            }
            Divider()
            Spacer(modifier = Modifier.height(40.dp))
            val currencies = currencies.observeAsState().value
            if (user == null || currencies !is ResultState.Success) {
                CircularProgressLoader()
            } else {
                ExchangeSubtitle()
                Spacer(modifier = Modifier.height(20.dp))
                ExchangeForm(
                    { from, to, amount ->
                        exchangeCurrency(amount, from, to)
                    },
                    user.balances.map { requireNotNull(it.code) },
                    currencies.data.map { it.code }
                )
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
