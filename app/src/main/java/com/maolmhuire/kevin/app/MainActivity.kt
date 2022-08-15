package com.maolmhuire.kevin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.maolmhuire.kevin.core.entity.Exchange
import com.maolmhuire.kevin.core.entity.ResultState
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: ExchangeCurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.currencies.observe(this) {
            if (it is ResultState.Success) {
                Timber.d("YEOW! Currencies")
            } else if (it is ResultState.Error) {
                Timber.d("NAWW! Currencies")
            }
        }

        viewModel.userData.observe(this) {
            Timber.d("User: ${it?.user?.name}")
            Timber.d("User Exchanges Size : ${it?.exchanges?.size}")
            Timber.d("User Balances Size : ${it?.balances?.size}")
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
        viewModel.exchangeCurrency("100", "EUR", "USD")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}