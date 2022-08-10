package com.maolmhuire.kevin.core.entity

data class User (
    val name: String = "Caoimhín Ó Maolmhuire",
    val balances: List<Balance>
)

data class Balance(
    val currency: Currency,
    val netBalance: Double
)

data class ExchangeHistory(
    val history: List<Exchange>
)
