package com.maolmhuire.kevin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ResultState
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ExchangeCurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeCurrencyView(viewModel)
        }
        viewModel.exchange.observe(this) {
            if (it is ResultState.Success) {
                val builder = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val adapter: JsonAdapter<Exchange> = builder.adapter(Exchange::class.java)
                val json = adapter.toJson(it.data)
                Timber.d("YEOW! $json")
            } else if (it is ResultState.Error)  {
                Timber.d("NAWW! exchange")
            }
        }
    }
}