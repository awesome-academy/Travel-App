package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Restaurant

interface RestaurantDatasource {
    interface Remote {
        fun searchRestaurantsByProperty(
            prameters: Map<String, String>,
            callback: OnDataCallback<String>
        )

        fun getDetailRestaurant(
            prameters: Map<String, String>,
            callback: OnDataCallback<String>
        )
    }

    interface Local {
        fun insertLocation(restaurant: Restaurant, callback: OnDataCallback<Boolean>)
        fun getDefaultParams(): MutableMap<String, String>
    }
}
