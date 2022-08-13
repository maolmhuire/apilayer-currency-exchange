package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.entity.Exchange
import javax.inject.Inject

class InsertExchangeHistoryUseCase @Inject constructor(private val localUserDao: LocalUserDao) {
    suspend operator fun invoke(exchange: Exchange) = localUserDao.insertExchange(exchange)
}