package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.ui.base.BaseRecyclerAdapter

class HotelAdapter : BaseRecyclerAdapter<Hotel, HotelViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(itemView)
    }
}
