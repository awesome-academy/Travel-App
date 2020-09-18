package com.sunasterisk.travelapp.ui.list.restaurant


import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.ui.base.BaseContract

interface RestaurantListContract {

    interface View : BaseContract.View<Presenter> {
        fun updateRestaurants(list: List<Restaurant>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getRestaurants(
            latitude: String,
            longtitude: String,
            mealType: String? = null,
            restaurantType: String? = null
        )
    }
}
