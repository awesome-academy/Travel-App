package com.sunasterisk.travelapp.data.source.local

import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.models.Location.Companion.TYPE_RESTAURANT
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.source.RestaurantDatasource
import com.sunasterisk.travelapp.data.source.local.dao.LocationFavouriteDAO
import com.sunasterisk.travelapp.data.source.local.preferences.PreferencesHelper
import com.sunasterisk.travelapp.utils.ApiEndpoint.CURRENCY
import com.sunasterisk.travelapp.utils.ApiEndpoint.LANGUAGE

class RestaurantLocalDatasource private constructor(
    private val locationDAO: LocationFavouriteDAO,
    private val preference: PreferencesHelper
) : RestaurantDatasource.Local {
    override fun insertLocation(restaurant: Restaurant, callback: OnDataCallback<Boolean>) {
        val location = Location(
            id = restaurant.id,
            type = TYPE_RESTAURANT,
            name = restaurant.name,
            location = restaurant.address,
            thumb = restaurant.imageThumbRestaurant,
            large = restaurant.imageLargeRestaurant,
            descriptionLocation = restaurant.descriptionRestaurant
        )
        LoadDataAsync<Location, Boolean>(callback) {
            locationDAO.insertLocationFavourite(location, preference.getCurrentUser())
        }
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
        private var instance: RestaurantLocalDatasource? = null

        fun getInstance(locationDAO: LocationFavouriteDAO, preference: PreferencesHelper) =
            instance ?: synchronized(this) {
                instance ?: RestaurantLocalDatasource(locationDAO, preference).also {
                    instance = it
                }
            }
    }
}
