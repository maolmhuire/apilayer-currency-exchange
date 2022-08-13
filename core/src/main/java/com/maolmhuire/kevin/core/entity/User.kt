package com.maolmhuire.kevin.core.entity

import androidx.room.*

class UserDetails {
    @Embedded
    var user: User? = null

    @Relation(parentColumn = "id", entityColumn = "user_id", entity = Balance::class)
    var balances: List<Balance> = emptyList()

    @Relation(parentColumn = "id", entityColumn = "user_id", entity = Exchange::class)
    var exchanges: List<Exchange> = emptyList()
}

@Entity(tableName = "user")
class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "name")
    var name: String? = null
}

@Entity(tableName = "balance")
class Balance {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "user_id")
    var userId: Long = 0

    @ColumnInfo(name = "code")
    var code: String? = null

    @ColumnInfo(name = "symbol")
    var symbol: String? = null

    @ColumnInfo(name = "netBalance")
    var netBalance: Double = 0.0
}
