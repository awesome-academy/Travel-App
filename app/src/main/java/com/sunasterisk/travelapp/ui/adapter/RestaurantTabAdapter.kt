package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.ui.adapter.RestaurantTabAdapter.LocationViewHolder
import com.sunasterisk.travelapp.utils.setImageUrl
import kotlinx.android.synthetic.main.item_destination.view.*

class RestaurantTabAdapter(private var onItemClick: (Location) -> Unit) :
    BaseRecyclerAdapter<Location, LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destination, parent, false)
        return LocationViewHolder(itemView, onItemClick)
    }

    class LocationViewHolder(
        itemView: View,
        onItemClick: (Location) -> Unit
    ) : BaseViewHolder<Location>(itemView, onItemClick) {
        override fun onBindData(itemData: Location) {
            super.onBindData(itemData)
            with(itemView) {
                val textGeo = resources.getQuantityString(
                    R.plurals.title_properties,
                    resources.getInteger(R.integer.integer_1),
                    itemData.propertiesNumber
                )
                val textRestaurant =
                    resources.getString(R.string.title_restaurant_address, itemData.location)
                val textHotel =
                    resources.getString(R.string.title_hotel_address, itemData.location)
                val textLocation =
                    resources.getString(R.string.title_location_address, itemData.location)
                textViewDestinationNameItem.text = itemData.name
                textViewResultType.text = when (itemData.type) {
                    Location.TYPE_GEO -> textGeo
                    Location.TYPE_RESTAURANT -> textRestaurant
                    Location.TYPE_HOTEL -> textHotel
                    else -> textLocation
                }
                imageDestinationItem.setImageUrl(itemData.thumb)
            }
        }
    }
}
