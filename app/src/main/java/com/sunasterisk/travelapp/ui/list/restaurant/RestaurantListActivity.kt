package com.sunasterisk.travelapp.ui.list.restaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseActivity

class RestaurantListActivity() : BaseActivity() {

    override val layoutResource get() = R.layout.activity_restaurant_list

    override fun initComponents() {
    }

    companion object {

        const val ARGUMENT_MEAL = "ARGUMENT_MEAL"
        const val ARGUMENT_RESTAURANT = "ARGUMENT_RESTAURANT"
        const val ARGUMENT_LATITUDE = "ARGUMENT_LATITUDE"
        const val ARGUMENT_LONGTITUDE = "ARGUMENT_LONGTITUDE"
        const val ARGUMENT_NAME = "ARGUMENT_NAME"
        const val BUNDLE_RESTAURANT = "BUNDLE_RESTAURANT"

        fun getRestaurantListIntent(
            context: Context,
            location: Location,
            mealParam: String? = null,
            restaurantParam: String? = null
        ): Intent {
            val bundle = Bundle()
                .apply {
                    mealParam?.let {
                        putString(ARGUMENT_MEAL, it)
                    }
                    restaurantParam?.let {
                        putString(ARGUMENT_RESTAURANT, it)
                    }
                    putString(ARGUMENT_LATITUDE, location.latitude)
                    putString(ARGUMENT_LONGTITUDE, location.longitude)
                    putString(ARGUMENT_NAME, location.name)
                }
            return Intent(context, RestaurantListActivity::class.java)
                .putExtra(BUNDLE_RESTAURANT, bundle)
        }
    }
}
