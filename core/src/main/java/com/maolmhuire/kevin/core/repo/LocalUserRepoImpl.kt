package com.maolmhuire.kevin.core.repo

import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.User
import com.maolmhuire.kevin.core.entity.UserDetails
import kotlinx.coroutines.flow.Flow

interface LocalUserRepo {
    suspend fun insertUser(user: User): Long

    suspend fun insertExchange(exchange: Exchange): Long

    suspend fun insertBalance(balance: Balance): Long

    suspend fun updateBalance(balance: Balance): Int

    suspend fun deleteBalance(balanceId: Long): Int

    fun getLocalUserFlow(): Flow<UserDetails?>

    suspend fun getUser(): UserDetails?
}

class LocalUserRepoImpl(private val dao: LocalUserDao) : LocalUserRepo  {

    override suspend fun insertUser(user: User): Long = dao.insertUser(user)

    override suspend fun insertExchange(exchange: Exchange): Long = dao.insertExchange(exchange)

    override suspend fun insertBalance(balance: Balance): Long = dao.insertBalance(balance)

    override suspend fun updateBalance(balance: Balance): Int = dao.updateBalance(balance)

    override suspend fun deleteBalance(balanceId: Long): Int = dao.deleteBalance(balanceId)

    override fun getLocalUserFlow(): Flow<UserDetails?> = dao.getLocalUserFlow()

    override suspend fun getUser(): UserDetails? = dao.getUser()
}