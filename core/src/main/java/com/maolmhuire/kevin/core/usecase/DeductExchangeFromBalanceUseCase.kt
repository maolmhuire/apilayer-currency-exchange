package com.maolmhuire.kevin.core.usecase

import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ResultState
import com.maolmhuire.kevin.core.exception.NegativeBalanceException
import com.maolmhuire.kevin.core.exception.NullBalanceException
import com.maolmhuire.kevin.core.exception.NullFeeException
import com.maolmhuire.kevin.core.repo.LocalUserRepo
import javax.inject.Inject

class DeductExchangeFromBalanceUseCase @Inject constructor(
    private val localUserRepo: LocalUserRepo
) {
    suspend operator fun invoke(exchange: Exchange): ResultState<Exchange> {
        if (exchange.fee == null) {
            return ResultState.Error(NullFeeException("Null fee value."))
        }

        val usrDtls = requireNotNull(localUserRepo.getUser())
        val fee = requireNotNull(exchange.fee)

        val fromBalance = usrDtls.balances.find { it.code == exchange.from }
            ?: return ResultState.Error(NullBalanceException("Null account balance."))

        val fromExchangeValue = (exchange.amount + fee.fromValueFee)
        fromBalance.takeIf { it.netBalance - fromExchangeValue > 0 }?.apply {
            netBalance -= fromExchangeValue
            if (netBalance == 0.0) {
                localUserRepo.deleteBalance(requireNotNull(id))
            } else {
                localUserRepo.updateBalance(this)
            }
        } ?: ResultState.Error(NegativeBalanceException("Negative account balance"))

        val toExchangeValue = (exchange.result - fee.toValueFee)
        usrDtls.balances.find { it.code == exchange.to }?.apply {
            netBalance += toExchangeValue

            localUserRepo.updateBalance(balance = this)
        } ?: Balance().apply {
            userId = requireNotNull(usrDtls.user?.id)
            code = exchange.to
            netBalance = toExchangeValue

            localUserRepo.insertBalance(balance = this)
        }

        return ResultState.Success(exchange)
    }
}