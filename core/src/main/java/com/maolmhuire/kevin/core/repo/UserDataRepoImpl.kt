package com.maolmhuire.kevin.core.repo

import com.maolmhuire.kevin.core.datasource.UserDataSource
import com.maolmhuire.kevin.core.entity.User
import kotlinx.coroutines.flow.Flow

interface UserDataRepo {

}

class UserDataRepoImpl(userDataSource: UserDataSource): UserDataRepo {
    val userData: Flow<User> = userDataSource.userData
}