package com.lazygeniouz.hiltpractise.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.lazygeniouz.hiltpractise.R
import com.lazygeniouz.hiltpractise.adapter.CurrencyInfoAdapter
import com.lazygeniouz.hiltpractise.data.viewmodels.CurrencyInfoViewModel
import com.lazygeniouz.hiltpractise.databinding.ActivityCurrencyInfoBinding
import com.lazygeniouz.hiltpractise.extensions.capitalize
import dagger.hilt.android.AndroidEntryPoint

/**
 * The [DetailActivity] displays the information of
 * a crypto-currency fetch from a public api.
 *
 * Also an [AndroidEntryPoint] for **Hilt**.
 */

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(R.layout.activity_currency_info) {

    private val currencyInfoViewModel: CurrencyInfoViewModel by viewModels()
    private lateinit var detailActivity: ActivityCurrencyInfoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailActivity = ActivityCurrencyInfoBinding.inflate(layoutInflater)
        setContentView(detailActivity.root)
        setSupportActionBar(detailActivity.myToolbar)
        supportActionBar?.apply {
            title = intent.getStringExtra("symbol")?.capitalize()
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }

        currencyInfoViewModel.observe(this) { currencyInfo ->
            detailActivity.loading.visibility = View.GONE
            detailActivity.dataHolder.visibility = View.VISIBLE
            detailActivity.currencyIcon.load(currencyInfo.imageUrl)
            detailActivity.currencyName.text = currencyInfo.name
            detailActivity.currencySymbol.text = "(${currencyInfo.symbol})"
            detailActivity.currencyInfoList.adapter = CurrencyInfoAdapter(currencyInfo)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}