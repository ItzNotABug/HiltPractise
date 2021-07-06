package com.lazygeniouz.hiltpractise.data.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lazygeniouz.hiltpractise.data.api.CoinGeckoApi
import com.lazygeniouz.hiltpractise.data.models.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This [ViewModel] loads and provides the list of top 10 Crypto-Currencies
 *
 * @param apiInterface Instance of API Service, Injected via **Hilt**
 */

@HiltViewModel
class CurrencyViewModel
@Inject
constructor(private val apiInterface: CoinGeckoApi) : ViewModel() {

    private var pricesData = MutableLiveData<List<Currency>>()

    init {
        loadPrices()
    }

    fun observe(
        owner: LifecycleOwner,
        currencyList: (List<Currency>) -> Unit,
    ) = pricesData.observe(owner) { currencyList.invoke(it) }

    private fun loadPrices() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val currencies = apiInterface
                    .getCurrencyList()
                    .body()
                    .orEmpty()
                    .subList(0, 9)
                pricesData.postValue(currencies)
            }
        }
}