package com.sunasterisk.travelapp.data.source

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location

interface LocationDatasource {
    interface Local {
        fun insertLocation(location: Location): Boolean
        fun getAllLocations(): List<Location>
        fun deleteLocation(location: Location): Boolean
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
