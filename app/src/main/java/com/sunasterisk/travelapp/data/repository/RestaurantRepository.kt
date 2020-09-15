package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Restaurant

interface RestaurantRepository {
    fun searchRestaurantsByProperty(
        latitude: String,
        longtitude: String,
        mealType: String?,
        restaurantType: String?,
        callback: OnDataCallback<List<Restaurant>>
    )

    fun getDetailRestaurant(
        locationId: String,
        callback: OnDataCallback<Restaurant>
    )

    fun insertLocation(restaurant: Restaurant, callback: OnDataCallback<Boolean>)
    fun getDefaultParams(): MutableMap<String, String>
}
