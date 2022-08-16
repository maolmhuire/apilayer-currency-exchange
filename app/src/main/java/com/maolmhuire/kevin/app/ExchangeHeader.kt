package com.maolmhuire.kevin.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.User

@Composable
fun ExchangeHeader(
    user: User,
    balances: List<Balance>
) {
    Column {
        HeaderSummary(user)
        BalanceSummary(balances = balances)
    }
}

@Composable
fun HeaderSummary(user: User) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.kevin_of_glendalough),
            contentDescription = "An image of Kevin of Glendalough",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = user.name ?: "N/A",
                color = MaterialTheme.colors.primaryVariant,
                style = MaterialTheme.typography.subtitle2,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Interests: Currency conversion and old religious figures.",
                style = MaterialTheme.typography.body2,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun BalanceSummary(balances: List<Balance>) {
    Row(modifier = Modifier.height(110.dp)) {
        LazyRow {
            items(items = balances, itemContent = { item ->
                BalanceItem(item.code!!, item.netBalance)
                Divider(
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
            })
        }
    }
}

@Composable
fun BalanceItem(code: String, netBalance: Double) {
    Column(Modifier.padding(all = 12.dp)) {
        Text(
            text = "$code Account",
            color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.subtitle2,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Balance: ${netBalance.toCurrencyDisplayValue(code)}",
            style = MaterialTheme.typography.body2,
            fontSize = 18.sp
        )
        Text(
            text = code.toCurrencyDisplayName(),
            style = MaterialTheme.typography.body2,
            fontSize = 12.sp
        )
    }
}