package com.lazygeniouz.hiltpractise.data.api

import com.lazygeniouz.hiltpractise.data.models.Currency
import com.lazygeniouz.hiltpractise.data.models.CurrencyInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API Interface for Retrofit
 */
interface CoinGeckoApi {
    /**
     * Returns a list of crypto-currency in a [Currency] object
     */
    @GET("markets?vs_currency=inr")
    suspend fun getCurrencyList(): Response<List<Currency>>

    /**
     * Returns a [CurrencyInfo] object containing information about a specific symbol
     *
     * @param symbol Name / Id of the currency
     * (e.g: bitcoin, ethereum, doge, etc)
     */
    @GET("markets?vs_currency=inr")
    suspend fun getCurrencyInfo(@Query("ids") symbol: String): Response<List<CurrencyInfo>>
}