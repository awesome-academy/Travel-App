package com.sunasterisk.travelapp.ui.home.restaurant

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseContract

interface RestaurantContract {
    interface View : BaseContract.View<Presenter> {
        fun updateLocations(list: List<Location>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun searchLocations(input: String)
    }
}
