package com.sunasterisk.travelapp.ui.detail.restaurant

import android.content.Context
import android.content.Intent
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.ui.base.BaseActivity

class RestaurantDetailActivity : BaseActivity() {

    override val layoutResource get() = R.layout.activity_restaurant_detail

    override fun initComponents() {
    }

    companion object {

        private const val EXTRA_RESTAURANT_NAME = "EXTRA_RESTAURANT_NAME"
        private const val EXTRA_RESTAURANT_ID = "EXTRA_RESTAURANT_ID"

        fun getIntent(context: Context, restaurant: Restaurant) =
            Intent(context, RestaurantDetailActivity::class.java)
                .putExtra(EXTRA_RESTAURANT_ID, restaurant.id)
                .putExtra(EXTRA_RESTAURANT_NAME, restaurant.name)

    }
}
