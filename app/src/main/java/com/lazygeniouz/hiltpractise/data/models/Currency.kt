package com.lazygeniouz.hiltpractise.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency(
    val id: String,
    val name: String,
    val symbol: String,

    @Expose
    @SerializedName("image")
    val imageUrl: String,

    @Expose
    @SerializedName("current_price")
    val currentPrice: String
)
