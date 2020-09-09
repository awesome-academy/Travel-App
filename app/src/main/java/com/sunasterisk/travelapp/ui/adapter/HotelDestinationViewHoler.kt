package com.sunasterisk.travelapp.ui.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.utils.showToast
import kotlinx.android.synthetic.main.item_destination.view.*

class HotelDestinationViewHolder(
    itemView: View
) : BaseViewHolder<Location>(itemView) {

    override fun onBindData(itemData: Location) {
        super.onBindData(itemData)
        with(itemView) {
            textViewDestinationNameItem.text = itemData.name
            textViewPropertiesItem.text =
                resources.getString(R.string.title_properties, itemData.propertiesNumber)
            Glide.with(context).load(itemData.thumb).into(imageDestinationItem)
        }
    }

    override fun onHandleItemCLick(location: Location) = Unit
}
