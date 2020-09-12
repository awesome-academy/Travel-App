package com.sunasterisk.travelapp.ui.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_hotel.view.*

class HotelViewHolder(itemView: View) : BaseViewHolder<Hotel>(itemView) {

    override fun onBindData(itemData: Hotel) {
        super.onBindData(itemData)
        with(itemView) {
            textViewHotelNameItem.text = itemData.name
            textViewTypeItem.text = itemData.type
            Glide.with(context).load(itemData.imageThumbHotel).into(imageHotelItem)
            textViewPrice.text = itemData.price
            textViewRating.rating = itemData.rating_count.toFloat()
        }
    }
}
