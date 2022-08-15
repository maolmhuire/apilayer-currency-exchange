package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.repo.LocalUserRepo
import javax.inject.Inject

class GetLocalUserFlowUseCase @Inject constructor(private val localUserRepo: LocalUserRepo) {
    operator fun invoke() = localUserRepo.getLocalUserFlow()
}