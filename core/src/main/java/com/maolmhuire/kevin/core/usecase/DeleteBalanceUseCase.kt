package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.db.LocalUserDao
import javax.inject.Inject

class DeleteBalanceUseCase @Inject constructor(private val localUserDao: LocalUserDao) {
    suspend operator fun invoke(balanceId: Long) = localUserDao.deleteBalance(balanceId)
}