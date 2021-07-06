package com.lazygeniouz.hiltpractise.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lazygeniouz.hiltpractise.activity.DetailActivity
import com.lazygeniouz.hiltpractise.data.models.Currency
import com.lazygeniouz.hiltpractise.databinding.ItemCurrencyBinding
import com.lazygeniouz.hiltpractise.extensions.prettyCount

/**
 * RecyclerView adapter to show a list of top 10 crypto-currencies
 *
 * @param currencyList List of Crypto-Currencies to display
 */
class CurrencyListAdapter(private val currencyList: List<Currency>) :
    RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val currency = getCurrency(position)
        viewHolder.bindCurrency(currency)
    }

    private fun getCurrency(position: Int) = currencyList[position]

    override fun getItemCount() = currencyList.size

    class ViewHolder(binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {
        private val icon = binding.currencyIcon
        private val name = binding.currencyName
        private val symbol = binding.currencySymbol
        private val price = binding.currencyPrice
        private val cardView = binding.cardView

        @SuppressLint("SetTextI18n")
        fun bindCurrency(currency: Currency) = apply {
            icon.load(currency.imageUrl)
            name.text = currency.name
            symbol.text = "Symbol: ${currency.symbol}"
            price.text = "₹${currency.currentPrice.prettyCount()}"
            cardView.setOnClickListener {
                it.context.startActivity(
                    Intent(
                        it.context,
                        DetailActivity::class.java
                    ).putExtra("symbol", currency.id)
                )
            }
        }
    }
}