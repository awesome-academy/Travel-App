package com.sunasterisk.travelapp.data.source.local

import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.source.LocationDatasource
import com.sunasterisk.travelapp.data.source.local.dao.LocationFavouriteDAO
import com.sunasterisk.travelapp.data.source.local.preferences.PreferencesHelper
import com.sunasterisk.travelapp.utils.ApiEndpoint.CURRENCY
import com.sunasterisk.travelapp.utils.ApiEndpoint.LANGUAGE

class LocationLocalDatasource private constructor(
    private val locationDAO: LocationFavouriteDAO,
    private val preference: PreferencesHelper
) : LocationDatasource.Local {
    override fun insertLocation(location: Location, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<Unit, Boolean>(callback) {
            locationDAO.insertLocationFavourite(location, preference.getCurrentUser())
        }.execute(Unit)
    }

    override fun getAllLocations(callback: OnDataCallback<List<Location>>) {
        LoadDataAsync<Unit, List<Location>>(callback) {
            locationDAO.getAllLocationsFavourite(preference.getCurrentUser())
        }.execute(Unit)
    }

    override fun deleteLocation(location: Location, callback: OnDataCallback<Boolean>) {
        LoadDataAsync<Unit, Boolean>(callback) {
            locationDAO.deleteLocationFavourite(location, preference.getCurrentUser())
        }.execute(Unit)
    }

    override fun getDefaultParams(): MutableMap<String, String> {
        val setting = preference.getSetting()
        return mutableMapOf(
            LANGUAGE to setting.language,
            CURRENCY to setting.currency
        )
    }

    companion object {
        @Volatile
        private var instance: LocationLocalDatasource? = null

        fun getInstance(locationDAO: LocationFavouriteDAO, preference: PreferencesHelper) =
            instance ?: synchronized(this) {
                instance ?: LocationLocalDatasource(locationDAO, preference).also {
                    instance = it
                }
            }
    }
}
