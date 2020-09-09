package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback

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
    }
}
