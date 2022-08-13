package com.maolmhuire.kevin.core.exception

class NullFeeException(override val message: String?) : Exception(message)
class NullBalanceException(override val message: String?) : Exception(message)
class NegativeBalanceException(override val message: String?) : Exception(message)