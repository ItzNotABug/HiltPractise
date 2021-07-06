package com.lazygeniouz.hiltpractise.data.viewmodels

import androidx.lifecycle.*
import com.lazygeniouz.hiltpractise.data.api.CoinGeckoApi
import com.lazygeniouz.hiltpractise.data.models.CurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * This [ViewModel] loads and provides the data of a particular Crypto-Currency
 *
 * @param apiInterface Instance of API Service, Injected via **Hilt**
 * @param savedStateHandle Instance of SavedStateHandle for retrieving the symbol name
 * from activity intent data. Injected automatically via **Hilt**
 */
@HiltViewModel
class CurrencyInfoViewModel
@Inject
constructor(
    private val apiInterface: CoinGeckoApi,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var pricesData = MutableLiveData<CurrencyInfo>()

    init { loadCurrencyInfo() }

    fun observe(
        owner: LifecycleOwner,
        currencyList: (CurrencyInfo) -> Unit,
    ) = pricesData.observe(owner) { currencyList.invoke(it) }

    private fun loadCurrencyInfo() =
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val currencyInfo = apiInterface
                    .getCurrencyInfo(savedStateHandle.get<String>("symbol")!!)
                    .body()!![0]
                pricesData.postValue(currencyInfo)
            }
        }
}

