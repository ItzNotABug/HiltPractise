package com.lazygeniouz.hiltpractise.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.lazygeniouz.hiltpractise.adapter.CurrencyListAdapter
import com.lazygeniouz.hiltpractise.data.viewmodels.CurrencyViewModel
import com.lazygeniouz.hiltpractise.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * The [MainActivity] displays the list of
 * crypto-currencies fetch from a public api.
 *
 * Also an [AndroidEntryPoint] for **Hilt**.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val currencyViewModel: CurrencyViewModel by viewModels()
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        setSupportActionBar(activityMainBinding.myToolbar)
        activityMainBinding.myToolbar.title = "HiltCrypto"

        val recyclerView = activityMainBinding.cryptoCurrencyList
        currencyViewModel.observe(this) { currencyList ->
            recyclerView.adapter = CurrencyListAdapter(currencyList)
        }
    }
}