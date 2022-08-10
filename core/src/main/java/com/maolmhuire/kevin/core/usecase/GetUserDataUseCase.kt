package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.entity.User
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor() {
    suspend operator fun invoke() = ResultState.Success(User())
}