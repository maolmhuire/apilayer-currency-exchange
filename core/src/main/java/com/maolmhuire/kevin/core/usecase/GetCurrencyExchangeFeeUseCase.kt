package com.maolmhuire.kevin.core.usecase

import android.text.format.DateUtils
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
    suspend operator fun invoke(exchange: Exchange): ResultState<Exchange> {
        with(exchange) {
            val usr = requireNotNull(localUserRepo.getUser())
            val builder = ExchangeFeeBuilder(
                usr.exchanges.count { DateUtils.isToday(it.timestamp) },
                from,
                to
            )

            if (builder.canCalculateFeeLocally()) {
                fee = builder.calculateFeesLocal(this.amount, rate)
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