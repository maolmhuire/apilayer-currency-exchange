package com.maolmhuire.kevin.core.db

import androidx.room.*
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.User
import com.maolmhuire.kevin.core.entity.UserDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalUserDao {

    @Insert
    suspend fun insertUser(user: User): Long

    @Insert
    suspend fun insertExchange(exchange: Exchange): Long

    @Insert
    suspend fun insertBalance(balance: Balance): Long

    @Query("DELETE FROM balance WHERE id = :balanceId")
    suspend fun deleteBalance(balanceId: Long): Int

    @Transaction
    @Query("SELECT * FROM user LIMIT 1")
    fun getLocalUserFlow(): Flow<UserDetails>

    @Transaction
    @Query("SELECT * FROM user LIMIT 1")
    suspend fun getUser(): UserDetails?
}