package com.maolmhuire.kevin.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ExchangeFee
import com.maolmhuire.kevin.core.entity.User

@Database(
    entities = [User::class, Balance::class, Exchange::class, ExchangeFee::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun localUserDao(): LocalUserDao
}