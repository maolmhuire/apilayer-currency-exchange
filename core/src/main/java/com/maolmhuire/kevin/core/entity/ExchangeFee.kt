package com.maolmhuire.kevin.core.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_fee")
data class ExchangeFee(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exchange_fee_id")
    var exchangeFeeId: Long? = null,
    @ColumnInfo(name = "from_value_fee")
    val fromValueFee: Double = 0.0,
    @ColumnInfo(name = "to_value_fee")
    val toValueFee: Double = 0.0
)

