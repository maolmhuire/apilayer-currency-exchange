package com.maolmhuire.kevin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.maolmhuire.kevin.core.entity.ResultState
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
            if (it is ResultState.Success) {
                Timber.d("YEOW! User")
            } else if (it is ResultState.Error)  {
                Timber.d("NAWW! User")
            }
        }

        viewModel.exchange.observe(this) {
            if (it is ResultState.Success) {
                Timber.d("YEOW! exchange")
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