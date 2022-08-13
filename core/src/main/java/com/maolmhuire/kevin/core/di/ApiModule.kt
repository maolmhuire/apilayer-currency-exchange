package com.maolmhuire.kevin.core.di

import android.content.Context
import androidx.room.Room
import com.maolmhuire.kevin.core.apiservice.CurrencyExchangeService
import com.maolmhuire.kevin.core.db.AppDatabase
import com.maolmhuire.kevin.core.db.LocalUserDao
import com.maolmhuire.kevin.core.repo.CurrencyRepo
import com.maolmhuire.kevin.core.repo.CurrencyRepoImpl
import com.maolmhuire.kevin.core.repo.LocalUserRepo
import com.maolmhuire.kevin.core.repo.LocalUserRepoImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    const val BASE_URL = "https://api.apilayer.com/"
    const val TOKEN = "i1PMfVUbzMxWJlyZ8ONvqnjbdMZnbKYF"

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "kevin-db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesLocalUserDao(appDatabase: AppDatabase): LocalUserDao {
        return appDatabase.localUserDao()
    }

    @Provides
    @Singleton
    fun providesLocalUserRepo(localUserDao: LocalUserDao): LocalUserRepo {
        return LocalUserRepoImpl(localUserDao)
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .callTimeout(20L, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val header = chain
                    .request()
                    .newBuilder()
                    .header("apiKey", TOKEN)
                    .build()
                chain.proceed(header)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        )
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesCurrencyExchangeService(retrofit: Retrofit): CurrencyExchangeService =
        retrofit.create(CurrencyExchangeService::class.java)

    @Singleton
    @Provides
    fun providesCurrencyRepo(currencyExchangeService: CurrencyExchangeService): CurrencyRepo =
        CurrencyRepoImpl(currencyExchangeService)

}