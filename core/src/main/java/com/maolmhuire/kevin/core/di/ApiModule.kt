package com.maolmhuire.kevin.core.di

import com.maolmhuire.kevin.core.apiservice.CurrencyExchangeService
import com.maolmhuire.kevin.core.datasource.CurrencyDataSource
import com.maolmhuire.kevin.core.repo.CurrencyRepo
import com.maolmhuire.kevin.core.repo.CurrencyRepoImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    const val BASE_URL = "https://api.apilayer.com/exchangerates_data/latest"
    const val TOKEN = "0fiuZFh4"

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
            .addInterceptor { chain ->
                val url = chain
                    .request()
                    .url
                    .newBuilder()
                    .addQueryParameter("apiKey", TOKEN)
                    .build()
                chain.proceed(chain.request().newBuilder().url(url).build())
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
        CurrencyRepoImpl(CurrencyDataSource(currencyExchangeService))

}