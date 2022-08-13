package com.maolmhuire.kevin.core.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange")
data class Exchange(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exchange_id")
    var exchangeId: Long? = null,
    @ColumnInfo(name = "user_id")
    var userId: Long = 0,
    @ColumnInfo(name = "amount")
    var amount: Double,
    @ColumnInfo(name = "rate")
    var rate: Double,
    @ColumnInfo(name = "timestamp")
    var timestamp: Long,
    @ColumnInfo(name = "result")
    var result: Double,
    @ColumnInfo(name = "from")
    var from: String,
    @ColumnInfo(name = "to")
    var to: String,
    @Embedded
    var fee: ExchangeFee? = null
)