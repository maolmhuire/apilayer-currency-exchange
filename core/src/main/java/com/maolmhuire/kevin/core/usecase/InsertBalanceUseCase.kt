package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.entity.Balance
import javax.inject.Inject

class InsertBalanceUseCase @Inject constructor(private val localUserDao: LocalUserDao) {
    suspend operator fun invoke(balance: Balance) = localUserDao.insertBalance(balance)
}