package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.entity.Exchange
import javax.inject.Inject

class InsertExchangeHistoryUseCase @Inject constructor(private val localUserDao: LocalUserDao) {
    suspend operator fun invoke(exchange: Exchange) {
        val usrDtls = requireNotNull(localUserDao.getUser())
        exchange.userId = requireNotNull(usrDtls.user?.id)
        localUserDao.insertExchange(exchange)
    }
}