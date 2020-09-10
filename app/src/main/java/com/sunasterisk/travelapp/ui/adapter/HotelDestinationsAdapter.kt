package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter

class HotelDestinationsAdapter :
    BaseRecyclerAdapter<Location, HotelDestinationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelDestinationViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_destination, parent,false)
        return HotelDestinationViewHolder(itemView)
    }
}
