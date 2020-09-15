package com.sunasterisk.travelapp.data.source.local

import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Location.Companion.TYPE_HOTEL
import com.sunasterisk.travelapp.data.source.HotelDatasource
import com.sunasterisk.travelapp.data.source.local.dao.LocationFavouriteDAO
import com.sunasterisk.travelapp.data.source.local.preferences.PreferencesHelper
import com.sunasterisk.travelapp.utils.ApiEndpoint.CURRENCY
import com.sunasterisk.travelapp.utils.ApiEndpoint.LANGUAGE

class HotelLocalDatasource private constructor(
    private val locationDAO: LocationFavouriteDAO,
    private val preference: PreferencesHelper
) : HotelDatasource.Local {
    override fun insertLocation(hotel: Hotel, callback: OnDataCallback<Boolean>) {
        val location = Location(
            hotel.id,
            TYPE_HOTEL,
            hotel.name,
            hotel.address,
            hotel.imageThumbHotel,
            hotel.imageLargeHotel,
            hotel.descriptionHotel
        )
        LoadDataAsync<Unit, Boolean>(callback) {
            locationDAO.insertLocationFavourite(location, preference.getCurrentUser())
        }.execute(Unit)
    }

    override fun getDefaultParams(): Map<String, String> {
        val setting = preference.getSetting()
        return mutableMapOf(
            LANGUAGE to setting.language,
            CURRENCY to setting.currency
        )
    }

    companion object {
        @Volatile
        private var instance: HotelLocalDatasource? = null

        fun getInstance(locationDAO: LocationFavouriteDAO, preference: PreferencesHelper) =
            instance ?: synchronized(this) {
                instance ?: HotelLocalDatasource(locationDAO, preference).also {
                    instance = it
                }
            }
    }
}
