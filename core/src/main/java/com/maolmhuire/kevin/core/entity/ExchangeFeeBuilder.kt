package com.maolmhuire.kevin.core.entity

class ExchangeFeeBuilder(
    private val exchangesToday: Int,
    private val exchangeFromCurCode: String,
    private val exchangeToCurCode: String
) {

    companion object {
        private const val LESS_THAN_5_EXCHANGES_RATE = 0.007
        private const val GREATER_THAN_15_EXCHANGES_BASE_RATE = 0.012
        private const val ADDITIONAL_EURO_EQUIV_FEE_RATE = 0.3
    }

    fun calculateFeesLocal(
        exchangeAmount: Double,
        rate: Double
    ): ExchangeFee {
        return if (exchangesToday > 15 && isEuroExchange()) {
            ExchangeFee(fromValueFee = 0.0, toValueFee = calculateLocalEuroValueExchangeFee(rate))
        } else if (exchangesToday > 5) {
            ExchangeFee(fromValueFee = exchangeAmount * LESS_THAN_5_EXCHANGES_RATE, toValueFee = 0.0)
        } else {
            ExchangeFee(fromValueFee = 0.0, toValueFee = 0.0)
        }
    }

    fun calculateFeesWithRemoteEuroFee(
        exchangeResult: Double,
        remoteEuroFeeValue: Double
    ): ExchangeFee = ExchangeFee(
        fromValueFee = 0.0,
        toValueFee = (exchangeResult * GREATER_THAN_15_EXCHANGES_BASE_RATE) + remoteEuroFeeValue
    )

    private fun isEuroExchange(): Boolean =
        ("EUR".equals(exchangeFromCurCode, true) ||
                "EUR".equals(exchangeToCurCode, true))

    private fun calculateLocalEuroValueExchangeFee(rate: Double): Double =
        if (exchangeToCurCode == "EUR") 0.3 else rate * ADDITIONAL_EURO_EQUIV_FEE_RATE

    fun canCalculateFeeLocally() = (exchangesToday <= 15) ||
            (exchangesToday > 15 && isEuroExchange())
}