package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback

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
}
