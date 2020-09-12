package com.sunasterisk.travelapp.ui.adapter

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.DestinationFilter
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseViewHolder
import com.sunasterisk.travelapp.ui.custom.TabName
import com.sunasterisk.travelapp.ui.list.hotel.HotelListActivity
import kotlinx.android.synthetic.main.item_destination.view.*

class HotelDestinationViewHolder(
    itemView: View
) : BaseViewHolder<Location>(itemView) {

    override fun onBindData(itemData: Location) {
        super.onBindData(itemData)
        with(itemView) {
            textViewDestinationNameItem.text = itemData.name
            textViewPropertiesItem.text =
                resources.getQuantityString(R.plurals.title_properties, itemData.propertiesNumber)
            Glide.with(context).load(itemData.thumb).into(imageDestinationItem)
        }
    }

    override fun onHandleItemCLick(mainItem: Location) {
        val intent = Intent(itemView.context, HotelListActivity::class.java)
            .apply {
                putExtra(
                    TabName.PERSON_TAB_KEY,
                    itemView.context.resources.getQuantityString(
                        R.plurals.title_filter_travelers,
                        DestinationFilter.adultCount, DestinationFilter.childrenCount
                    )
                )
                putExtra(
                    TabName.DATE_RANGE_TAB_KEY,
                    DestinationFilter.dateRange
                )
                putExtra(
                    TabName.LOCATION_TAB_KEY,
                    mainItem.name
                )
            }
        itemView.context.startActivity(intent)
    }
}
