package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location

interface LocationRepository {

    fun insertLocation(location: Location, callback: OnDataCallback<Boolean>)

    fun getAllLocations(callback: OnDataCallback<List<Location>>)

    fun deleteLocation(location: Location, callback: OnDataCallback<Boolean>)

    fun getDefaultParams(): MutableMap<String, String>

    fun searchLocationsByProperty(
        query: String,
        callback: OnDataCallback<List<Location>>
    )

    fun getPhotos(
        locationId: String,
        callback: OnDataCallback<List<String>>
    )
}
