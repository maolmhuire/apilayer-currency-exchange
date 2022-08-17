package com.maolmhuire.kevin.core

import com.maolmhuire.kevin.core.entity.ExchangeFeeBuilder
import org.junit.Assert
import org.junit.Test

class FeeBuilderTest {

    @Test
    fun `Can calculate fee locally - less than 5 - zero fee`() {
        val builder = ExchangeFeeBuilder(
            4, "EUR", "USD"
        )
        Assert.assertTrue(builder.canCalculateFeeLocally())

        val fee = builder.calculateFeesLocal(
            50.00,
            50.89,
            1.01790
        )

        Assert.assertEquals(0.0, fee.fromValueFee, 0.05)
    }

    @Test
    fun `Can calculate fee locally - Greater than 5 - Fee`() {
        val builder = ExchangeFeeBuilder(
            6, "EUR", "USD"
        )
        Assert.assertTrue(builder.canCalculateFeeLocally())

        val amount = 50.00
        val fee = builder.calculateFeesLocal(
            amount,
            50.89,
            1.01790
        )
        val testFee = amount * ExchangeFeeBuilder.GREATER_THAN_5_EXCHANGES_RATE
        Assert.assertEquals(testFee, fee.fromValueFee, 0.05)
    }

    @Test
    fun `Can calculate fee locally - Greater than 15 with EUR 'from' transfer - Fee`() {
        val builder = ExchangeFeeBuilder(
            16, "EUR", "USD"
        )
        Assert.assertTrue(builder.canCalculateFeeLocally())

        val amount = 50.00
        val result = 50.89
        val rate = 1.01790
        val fee = builder.calculateFeesLocal(amount, result, rate)

        val extraFee = rate * ExchangeFeeBuilder.ADDITIONAL_EURO_EQUIV_FEE_RATE
        val testFee = result * ExchangeFeeBuilder.GREATER_THAN_15_EXCHANGES_BASE_RATE + extraFee
        Assert.assertEquals(testFee, fee.toValueFee, 0.05)
    }

    @Test
    fun `Can calculate fee locally - Greater than 15 with EUR 'to' transfer - Fee`() {
        val builder = ExchangeFeeBuilder(
            16, "EUR", "USD"
        )
        Assert.assertTrue(builder.canCalculateFeeLocally())

        val amount = 50.00
        val result = 50.89
        val rate = 1.01790
        val fee = builder.calculateFeesLocal(amount, result, rate)

        val testFee = result * ExchangeFeeBuilder.GREATER_THAN_15_EXCHANGES_BASE_RATE + 0.30
        Assert.assertEquals(testFee, fee.toValueFee, 0.05)
    }
}