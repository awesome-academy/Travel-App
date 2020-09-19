package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.utils.convertToPrice
import com.sunasterisk.travelapp.utils.getPrice
import com.sunasterisk.travelapp.utils.removeAccent
import kotlinx.android.synthetic.main.item_restaurant.view.*
import java.util.*

class RestaurantListAdapter(private val onItemClick: (Restaurant) -> Unit) :
    BaseRecyclerAdapter<Restaurant, RestaurantListAdapter.RestaurantViewHolder>() {

    private val listRestaurant = mutableListOf<Restaurant>()

    override fun updateData(newItems: List<Restaurant>) {
        super.updateData(newItems)
        listRestaurant.clear()
        listRestaurant.addAll(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(itemView, onItemClick)
    }

    fun searchRestaurant(charText: String) {
        val query = charText
            .toLowerCase(Locale.getDefault())
            .removeAccent()
        if (query.isEmpty()) {
            super.updateData(listRestaurant)
        } else {
            val tmpListRestaurant = listRestaurant.filter {
                it.name
                    .toLowerCase(Locale.getDefault())
                    .contains(query)
            }
            super.updateData(tmpListRestaurant)
        }
    }

    fun filter(sortByPrice: Boolean, maxPrice: Int, minRating: Int) {
        val tmpListRestaurant = listRestaurant.filter {
            it.price.getPrice() <= maxPrice && it.ratingCount >= minRating
        }
        if (sortByPrice) {
            super.updateData(tmpListRestaurant.sortedBy { it.price.getPrice() })
        } else {
            super.updateData(tmpListRestaurant.sortedBy { it.ratingCount})
        }
    }

    class RestaurantViewHolder(
        itemView: View,
        onItemClick: (Restaurant) -> Unit
    ) : BaseViewHolder<Restaurant>(itemView, onItemClick) {
        override fun onBindData(itemData: Restaurant) {
            super.onBindData(itemData)
            with(itemView) {
                itemData.apply {
                    textViewRestaurantNameItem.text = name
                    textViewPriceRestaurant.text = price.convertToPrice()
                    ratingBarRestaurant.rating = ratingCount
                    Glide.with(context).load(imageThumbRestaurant).into(imageRestaurantItem)
                }
            }
        }
    }
}
