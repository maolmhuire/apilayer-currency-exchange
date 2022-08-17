package com.maolmhuire.kevin.core

import com.maolmhuire.kevin.core.Mocks.mockCurrencyRepo
import com.maolmhuire.kevin.core.Mocks.mockLocalUserRepo_Fees
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ExchangeFee
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.usecase.GetCurrencyExchangeFeeUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetCurrencyFeeUseCaseUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get Fee from Exchange - Greater than 5`() = runTest {
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

        val result = GetCurrencyExchangeFeeUseCase(mockCurrencyRepo, mockLocalUserRepo_Fees)
            .invoke(exchange) { true }
        advanceUntilIdle()
        Assert.assertTrue(result is ResultState.Success)
        val data = (result as ResultState.Success).data
        Assert.assertTrue(data.fee != null)
        Assert.assertTrue(data.fee!!.fromValueFee > 0.0)
    }
}