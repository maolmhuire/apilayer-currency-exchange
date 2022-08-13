package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.db.LocalUserDao
import javax.inject.Inject

class GetLocalUserFlowUseCase @Inject constructor(private val localUserDao: LocalUserDao) {
    suspend operator fun invoke() = localUserDao.getLocalUserFlow()
}