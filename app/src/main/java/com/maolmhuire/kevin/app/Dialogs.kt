package com.maolmhuire.kevin.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
@Preview
fun KevinDialogPreview() {
    KevinDialog(
        title = "Title",
        body = "This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. This is a piece of text. ",
        onPositiveClicked = {  },
        onDismiss = {}
    )
}

@Composable
fun UnexpectedErrorDialog() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        KevinDialog(
            title = stringResource(R.string.error),
            body = stringResource(R.string.unexpected_error_message),
            showCancel = false,
            onPositiveClicked = { openDialog.value = false }
        )
    }
}

@Composable
fun NetworkErrorDialog() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        KevinDialog(
            title = stringResource(R.string.error),
            body = stringResource(R.string.network_error_message),
            showCancel = false,
            onPositiveClicked = { openDialog.value = false }
        )
    }
}

@Composable
fun ExchangeSuccessDialog(body: String) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        KevinDialog(
            title = stringResource(R.string.success),
            body = body,
            showCancel = false,
            onPositiveClicked = { openDialog.value = false }
        )
    }
}

@Composable
fun NegativeBalanceErrorDialog() {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        KevinDialog(
            title = stringResource(R.string.error),
            body = stringResource(R.string.negative_balance_message),
            showCancel = false,
            onPositiveClicked = { openDialog.value = false }
        )
    }
}

@Composable
fun CurrencyLoadFailure(onPositiveClicked: () -> Unit) {
    val openDialog = remember { mutableStateOf(true) }
    if (openDialog.value) {
        KevinDialog(
            title = stringResource(R.string.error),
            body = stringResource(R.string.currency_list_error),
            showCancel = true,
            positiveBtnText = stringResource(R.string.retry),
            onPositiveClicked = onPositiveClicked,
            onDismiss = { openDialog.value = false }
        )
    }
}

@Composable
fun KevinDialog(
    title: String,
    body: String,
    positiveBtnText: String = stringResource(R.string.ok),
    negativeBtnText: String = stringResource(R.string.cancel),
    showCancel: Boolean = true,
    onPositiveClicked: () -> Unit = { /* no-op */ },
    onDismiss: () -> Unit = { /* no-op */ }
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primaryVariant
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = body,
                    style = MaterialTheme.typography.body2
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    if (showCancel) {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = negativeBtnText,
                                color = MaterialTheme.colors.secondaryVariant,
                            )
                        }
                    }
                    TextButton(onClick = onPositiveClicked) {
                        Text(
                            text = positiveBtnText,
                            color = MaterialTheme.colors.secondaryVariant
                        )
                    }
                }
            }
        }
    }
}