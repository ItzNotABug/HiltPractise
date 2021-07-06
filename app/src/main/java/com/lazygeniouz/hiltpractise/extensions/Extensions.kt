package com.lazygeniouz.hiltpractise.extensions

import java.util.*
import kotlin.math.ln
import kotlin.math.pow

// Base API Url, free for public usage
fun getBaseUrl() = "https://api.coingecko.com/api/v3/coins/"

// Adds a "0" prefix if the number is less than 9
fun String.rankify(): String {
    return if (toInt() <= 9) "0$this"
    else this
}

/**
 * Capitalize the first char of a string using this extension
 * because kotlin's internal [kotlin.text.capitalize] is deprecated.
 */
fun String?.capitalize() =
    this?.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
        else it.toString()
    }

/**
 * Add a proper suffix for easy readability
 * with respect to the string / integer's actual value
 */
fun String?.prettyCount(): String {
    if (this == null) return "N/A"
    if (this.toFloat() < 1000) return this
    val exp = (ln(toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f%c", this.toFloat() / 1000.0.pow(exp.toDouble()), "KMGTPE"[exp - 1])
}