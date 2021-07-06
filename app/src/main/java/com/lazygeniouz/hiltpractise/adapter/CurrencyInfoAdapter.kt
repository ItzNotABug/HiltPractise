package com.lazygeniouz.hiltpractise.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lazygeniouz.hiltpractise.data.models.CurrencyInfo
import com.lazygeniouz.hiltpractise.databinding.ItemCurrencyInfoBinding
import com.lazygeniouz.hiltpractise.extensions.prettyCount
import com.lazygeniouz.hiltpractise.extensions.rankify

/**
 * RecyclerView adapter to show some information about the passed currency.
 *
 * @param currencyInfo CurrencyInfo contains some
 * relevant data about a crypto-currency
 */
class CurrencyInfoAdapter(private val currencyInfo: CurrencyInfo) :
    RecyclerView.Adapter<CurrencyInfoAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCurrencyInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        private val titleTextView = binding.infoTitle
        private val valueTextView = binding.infoValue

        fun bindInfo(title: String, value: String) {
            titleTextView.text = title
            valueTextView.text = value
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemCurrencyInfoBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int,
    ) {
        when (position) {
            0 -> holder.bindInfo("Rank: ",
                currencyInfo.marketRank.rankify())
            1 -> holder.bindInfo("Current Price: ",
                "₹" + currencyInfo.currentPrice.prettyCount())
            2 -> holder.bindInfo("Current Market Cap: ",
                "₹" + currencyInfo.marketCap.prettyCount())
            3 -> holder.bindInfo("Max Supply: ",
                currencyInfo.maxSupply.prettyCount())
        }
    }

    override fun getItemCount() = 4
}