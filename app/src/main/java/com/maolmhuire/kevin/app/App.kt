package com.maolmhuire.kevin.app

import android.app.Application
import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.entity.Balance
import com.maolmhuire.kevin.core.entity.User
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var localUserDao: LocalUserDao

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        mockUserData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun mockUserData() {
        GlobalScope.launch {
            val user = localUserDao.getUser()
            if (user != null) {
                val insert = localUserDao.insertUser(User().apply { name = "Caoimhín Ó Maolmhuire" })
                localUserDao.insertBalance(
                    Balance().apply {
                        userId = insert
                        code = "EUR"
                        netBalance = 1000.00
                    }
                )
            }
        }
    }
}