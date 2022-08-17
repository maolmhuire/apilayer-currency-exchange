package com.maolmhuire.kevin.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ResultState
import timber.log.Timber


@Composable
fun ExchangeForm(
    exchange: ResultState<Exchange>?,
    accountCurrencies: List<String>,
    currenciesAvailable: List<String>,
    onSubmit: (selectedFrom: String, selectedTo: String, amount: String) -> Unit
) {

    var selectedFromCurrency by remember { mutableStateOf(accountCurrencies.getOrNull(0) ?: "") }
    var selectedToCurrency by remember { mutableStateOf(currenciesAvailable.getOrNull(0) ?: "") }
    var amountEntered by remember { mutableStateOf("") }
    val regex = "\\d+\\.?\\d*".toRegex()

    Column {
        Row {
            Box(modifier = Modifier
                .weight(1f)
                .padding(start = 22.dp, end = 22.dp)) {
                ExposedDropdownMenuBox(
                    accountCurrencies,
                    stringResource(R.string.exchange_from_label)
                ) {
                    Timber.d("Selected From: $it")
                    selectedFromCurrency = it
                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .padding(start = 22.dp, end = 22.dp)
            ) {
                ExposedDropdownMenuBox(
                    currenciesAvailable,
                    stringResource(R.string.exchange_to_label)
                ) {
                    Timber.d("Selected To: $it")
                    selectedToCurrency = it
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        CurrencyValueInput(
            input = amountEntered,
            onValueChanged = {
                if (it.matches(regex) || (it.isEmpty() && amountEntered.isNotEmpty())) {
                    amountEntered = it
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (exchange !is ResultState.Loading) {
            SubmitButton {
                onSubmit(
                    selectedFromCurrency,
                    selectedToCurrency,
                    amountEntered
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownMenuBox(
    items: List<String>,
    labelName: String,
    onClick: (selected: String) -> Unit
) {

    var expanded: Boolean by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(items[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(labelName) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedIndicatorColor = MaterialTheme.colors.secondaryVariant,
                focusedLabelColor = MaterialTheme.colors.secondaryVariant,
                focusedTrailingIconColor = MaterialTheme.colors.secondaryVariant
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            items.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onClick.invoke(selectionOption)
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Composable
fun CurrencyValueInput(input: String, onValueChanged: (String) -> Unit) {
    TextField(
        value = input,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp),
        onValueChange = onValueChanged,
        label = { Text(stringResource(R.string.amount_subtitle)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = MaterialTheme.colors.secondaryVariant
        )
    )
}

@Composable
fun SubmitButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClick
        ) {
            Text(text = stringResource(R.string.exchange_currency))
        }
    }
}

@Composable
fun ExchangeSubtitle() {
    Text(
        modifier = Modifier.padding(start = 8.dp),
        text = stringResource(R.string.exchange_subtitle),
        style = MaterialTheme.typography.subtitle2,
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 14.sp
    )
}