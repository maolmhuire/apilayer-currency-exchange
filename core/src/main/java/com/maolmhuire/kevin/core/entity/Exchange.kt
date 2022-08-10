package com.maolmhuire.kevin.core.entity

data class Exchange(
    val amount: Double,
    val from: String,
    val to: String,
    val rate: Double,
    val timestamp: Long
)