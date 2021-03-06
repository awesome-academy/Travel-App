package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.User

interface LocationDatasource {
    interface Local {
        fun insertLocation(location: Location, callback: OnDataCallback<Boolean>)
        fun getAllLocations(callback: OnDataCallback<List<Location>>)
        fun deleteLocation(location: Location, callback: OnDataCallback<Boolean>)
        fun getDefaultParams(): MutableMap<String, String>
    }

    interface Remote {
        fun searchLocationsByProperty(
            prameters: Map<String, String>,
            callback: OnDataCallback<String>
        )

        fun getPhotos(
            prameters: Map<String, String>,
            callback: OnDataCallback<String>
        )
    }
}
