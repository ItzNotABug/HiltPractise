package com.lazygeniouz.hiltpractise.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyInfo(
    val name: String,
    val symbol: String,

    @Expose
    @SerializedName("image")
    val imageUrl: String,

    @Expose
    @SerializedName("current_price")
    val currentPrice: String,

    @Expose
    @SerializedName("max_supply")
    val maxSupply: String,

    @Expose
    @SerializedName("market_cap")
    val marketCap: String,

    @Expose
    @SerializedName("market_cap_rank")
    val marketRank: String,
)