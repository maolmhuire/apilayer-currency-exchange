package com.maolmhuire.kevin.core

import com.maolmhuire.kevin.core.entity.*
import com.maolmhuire.kevin.core.repo.CurrencyRepo
import com.maolmhuire.kevin.core.repo.LocalUserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal object Mocks {

    val userDetails = UserDetails().apply {
        user = User().apply {
            id = 1
            name = "Firstname Lastname"
        }

        exchanges = listOf(
            Exchange(
                1,
                1,
                50.00,
                1.01790,
                1660638628,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                2,
                1,
                50.00,
                1.01790,
                1660638628,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                3,
                1,
                50.00,
                1.01790,
                1660638628,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            )
        )

        balances = arrayListOf(
            Balance().apply {
                id = 1
                userId = 1
                code = "EUR"
                netBalance = 600.00
            },
            Balance().apply {
                id = 2
                userId = 1
                code = "USD"
                netBalance = 600.00
            },
            Balance().apply {
                id = 3
                userId = 1
                code = "GBP"
                netBalance = 600.00
            },
        )
    }

    val userDetails_Fees = UserDetails().apply {
        user = User().apply {
            id = 1
            name = "Firstname Lastname"
        }

        exchanges = listOf(
            Exchange(
                1,
                1,
                50.00,
                1.01790,
                1660736447,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                2,
                1,
                50.00,
                1.01790,
                1660736447,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                3,
                1,
                50.00,
                1.01790,
                1660736447,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                4,
                1,
                50.00,
                1.01790,
                1660736447,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                5,
                1,
                50.00,
                1.01790,
                1660736447,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            ),
            Exchange(
                6,
                1,
                50.00,
                1.01790,
                1660736447,
                50.89,
                "EUR",
                "USD",
                ExchangeFee()
            )
        )

        balances = arrayListOf(
            Balance().apply {
                id = 1
                userId = 1
                code = "EUR"
                netBalance = 600.00
            },
            Balance().apply {
                id = 2
                userId = 1
                code = "USD"
                netBalance = 600.00
            },
            Balance().apply {
                id = 3
                userId = 1
                code = "GBP"
                netBalance = 600.00
            },
        )
    }

    val mockLocalUserRepo = object : LocalUserRepo {

        override suspend fun insertUser(user: User): Long = 1

        override suspend fun insertExchange(exchange: Exchange): Long =
            with(userDetails.exchanges.toMutableList()) {
                add(exchange)
                userDetails.exchanges = this
                return 1
            }

        override suspend fun insertBalance(balance: Balance): Long =
            with(userDetails.balances.toMutableList()) {
                add(balance)
                userDetails.balances = this
                return 1
            }

        override suspend fun updateBalance(balance: Balance): Int = with(userDetails.balances) {
            find { it.code == balance.code }?.netBalance = balance.netBalance
            return 1
        }

        override suspend fun deleteBalance(balanceId: Long): Int =
            with(userDetails.balances.toMutableList()) {
                find { it.id == balanceId }?.run {
                    remove(this)
                }
                return 1
            }

        override fun getLocalUserFlow(): Flow<UserDetails?> = flow { userDetails }

        override suspend fun getUser(): UserDetails = userDetails

    }

    val mockLocalUserRepo_Fees = object : LocalUserRepo {

        override suspend fun insertUser(user: User): Long = 1

        override suspend fun insertExchange(exchange: Exchange): Long =
            with(userDetails_Fees.exchanges.toMutableList()) {
                add(exchange)
                userDetails_Fees.exchanges = this
                return 1
            }

        override suspend fun insertBalance(balance: Balance): Long =
            with(userDetails_Fees.balances.toMutableList()) {
                add(balance)
                userDetails_Fees.balances = this
                return 1
            }

        override suspend fun updateBalance(balance: Balance): Int = with(userDetails_Fees.balances) {
            find { it.code == balance.code }?.netBalance = balance.netBalance
            return 1
        }

        override suspend fun deleteBalance(balanceId: Long): Int =
            with(userDetails_Fees.balances.toMutableList()) {
                find { it.id == balanceId }?.run {
                    remove(this)
                }
                return 1
            }

        override fun getLocalUserFlow(): Flow<UserDetails?> = flow { userDetails_Fees }

        override suspend fun getUser(): UserDetails = userDetails_Fees

    }

    val mockCurrencyRepo = object : CurrencyRepo {

        override suspend fun getCurrencies(): ResultState<List<Currency>> =
            ResultState.Success(
                listOf(
                    Currency("EUR", ""),
                    Currency("USD", ""),
                    Currency("GBP", ""),
                    Currency("CAD", ""),
                    Currency("AUD", ""),
                    Currency("JPY", "")
                )
            )

        override suspend fun exchangeCurrencyValue(
            amount: String,
            from: String,
            to: String,
            date: String?
        ): ResultState<Exchange> = ResultState.Success(
            Exchange(
                4,
                1,
                amount.toDouble(),
                1.01790,
                1660638628,
                50.89,
                from,
                to,
                ExchangeFee()
            )
        )

        override suspend fun getExchangeEuroFeeRemote(exchangeToCurCode: String): ResultState<Double> =
            ResultState.Success(0.3)

    }
}