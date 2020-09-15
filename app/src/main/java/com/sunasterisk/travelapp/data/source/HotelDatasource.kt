package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Hotel

interface HotelDatasource {
    interface Remote {
        fun searchHotelsByProperty(
            prameters: Map<String, String>,
            callback: OnDataCallback<String>
        )

        fun getDetailHotel(
            prameters: Map<String, String>,
            callback: OnDataCallback<String>
        )
    }

    interface Local {
        fun insertLocation(hotel: Hotel, callback: OnDataCallback<Boolean>)
        fun getDefaultParams(): MutableMap<String, String>
    }
}
