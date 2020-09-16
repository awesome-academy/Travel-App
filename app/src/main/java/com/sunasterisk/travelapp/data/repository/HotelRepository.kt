package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Hotel

interface HotelRepository {

    fun searchHotelsByProperty(
        latitude: String,
        longtitude: String,
        checkInDate: String? = null,
        adults: Int? = null,
        childrens: Int? = null,
        callback: OnDataCallback<List<Hotel>>
    )

    fun getDetailHotel(
        locationId: String,
        callback: OnDataCallback<Hotel>
    )

    fun insertLocation(hotel: Hotel, callback: OnDataCallback<Boolean>)

    fun getDefaultParams(): Map<String, String>
}
