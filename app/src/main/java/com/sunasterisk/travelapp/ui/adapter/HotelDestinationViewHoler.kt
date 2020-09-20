package com.sunasterisk.travelapp.ui.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_destination.view.*

class HotelDestinationViewHolder(
    itemView: View,
    onItemClick: (Location) -> Unit
) : BaseViewHolder<Location>(itemView, onItemClick) {

    override fun onBindData(itemData: Location) {
        super.onBindData(itemData)
        with(itemView) {
            textViewDestinationNameItem.text = itemData.name
            textViewResultType.text = itemData.type
            Glide.with(context).load(itemData.thumb).into(imageDestinationItem)
        }
    }
}
