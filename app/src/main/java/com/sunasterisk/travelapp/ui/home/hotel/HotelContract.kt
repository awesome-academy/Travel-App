package com.sunasterisk.travelapp.ui.home.hotel

import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.ui.base.BaseContract

interface HotelContract {

    interface View : BaseContract.View<Presenter> {
        fun updateLocations(locations: List<Location>)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun searchLocations(input: String)
    }
}
