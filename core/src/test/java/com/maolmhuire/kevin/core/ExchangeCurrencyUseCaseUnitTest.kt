package com.maolmhuire.kevin.core

import com.maolmhuire.kevin.core.Mocks.mockCurrencyRepo
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.usecase.ExchangeCurrencyUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ExchangeCurrencyUseCaseUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Exchange Currency - Success`() = runTest {
        val useCase = ExchangeCurrencyUseCase(
            mockCurrencyRepo
        )
        val result = useCase.invoke(
            "50",
            "EUR",
            "USD"
        )
        Assert.assertTrue(result is ResultState.Success)
        val data = (result as ResultState.Success).data
        Assert.assertEquals(data.amount, 50.00, 0.005)
    }

}