package com.maolmhuire.kevin.core.datasource

import com.maolmhuire.kevin.core.entity.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDataSource() {
    val userData: Flow<User> = flow { emit(User()) }
}