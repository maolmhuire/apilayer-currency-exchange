package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ExchangeFeeBuilder
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.repo.CurrencyRepo
import com.maolmhuire.kevin.core.repo.LocalUserRepo
import javax.inject.Inject

class GetCurrencyExchangeFeeUseCase @Inject constructor(
    private val currencyRepo: CurrencyRepo,
    private val localUserRepo: LocalUserRepo
) {
    suspend operator fun invoke(exchange: Exchange, isToday: (timestamp: Long) -> Boolean): ResultState<Exchange> {
        with(exchange) {
            val usr = requireNotNull(localUserRepo.getUser())
            val count = usr.exchanges.count {
                isToday(it.timestamp * 1000L)
            }
            val builder = ExchangeFeeBuilder(count, from, to)

            if (builder.canCalculateFeeLocally()) {
                fee = builder.calculateFeesLocal(amount, result, rate)
                return ResultState.Success(this)
            } else {
                when (val euroExtraFee = currencyRepo.getExchangeEuroFeeRemote(to)) {
                    is ResultState.Success -> {
                        fee = builder.calculateFeesWithRemoteEuroFee(result, euroExtraFee.data)
                        return ResultState.Success(this)
                    }
                    is ResultState.Error -> {
                        return ResultState.Error(euroExtraFee.exception)
                    }
                    else -> {
                        return ResultState.Loading
                    }
                }
            }
        }
    }
}