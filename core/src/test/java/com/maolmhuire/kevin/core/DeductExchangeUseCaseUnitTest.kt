package com.maolmhuire.kevin.core

import com.maolmhuire.kevin.core.Mocks.mockLocalUserRepo
import com.maolmhuire.kevin.core.entity.*
import com.maolmhuire.kevin.core.usecase.DeductExchangeFromBalanceUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class DeductExchangeUseCaseUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Deduct Exchange from Balance - EUR to USD - No Fee`() = runTest {
        val useCase = DeductExchangeFromBalanceUseCase(mockLocalUserRepo)
        val fromCode = "EUR"
        val exchange = Exchange(
            4,
            1,
            50.00,
            1.01790,
            1660638628,
            50.89,
            fromCode,
            "USD",
            ExchangeFee()
        )

        val user = mockLocalUserRepo.getUser()
        val originalBalance = user?.balances?.find { it.code == fromCode }?.netBalance ?: 0.0
        val result = useCase.invoke(exchange)
        val updatedBalance = user?.balances?.find { it.code == fromCode }?.netBalance ?: 0.0
        Assert.assertTrue(result is ResultState.Success)
        Assert.assertTrue(updatedBalance < originalBalance)
    }
}