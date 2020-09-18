package com.sunasterisk.travelapp.ui.detail

import android.content.Context
import android.content.Intent
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.ui.adapter.DetailImageAdapter
import com.sunasterisk.travelapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hotel_detail.*

class HotelDetailActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_hotel_detail
    private val adapter = DetailImageAdapter()

    override fun initComponents() {
        bindMainContent()
    }

    private fun bindMainContent() {
        val hotelItem = intent.getParcelableExtra(EXTRA_HOTEL_ITEM) ?: Hotel()
        setUpHotelImageSlider(hotelItem.listImage)
        textViewHotelName.text = hotelItem.name
        textViewLocation.text = hotelItem.location
        textViewPrice.text = hotelItem.price
        textViewDetail.text = hotelItem.descriptionHotel
        collapsingToolbarLayout.title = hotelItem.name
    }

    private fun setUpHotelImageSlider(imageList: MutableList<String>) {
        viewPagerHotelThumb.adapter = adapter
        adapter.updateList(imageList)
    }

    companion object {
        const val EXTRA_HOTEL_ITEM = "EXTRA_HOTEL_ITEM"
        fun getIntent(context: Context, hotel: Hotel) =
            Intent(context, HotelDetailActivity::class.java).apply {
                putExtra(EXTRA_HOTEL_ITEM, hotel)
            }
    }
}
