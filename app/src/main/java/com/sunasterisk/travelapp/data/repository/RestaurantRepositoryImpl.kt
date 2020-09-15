package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Restaurant
import com.sunasterisk.travelapp.data.source.RestaurantDatasource
import com.sunasterisk.travelapp.utils.ApiEndpoint
import com.sunasterisk.travelapp.utils.ApiEndpoint.LOCATION_ID
import com.sunasterisk.travelapp.utils.ApiEndpoint.MEAL_TYPE
import com.sunasterisk.travelapp.utils.ApiEndpoint.RESTAURANT_TYPE
import com.sunasterisk.travelapp.utils.KeyResponse.DATA
import org.json.JSONException
import org.json.JSONObject

class RestaurantRepositoryImpl private constructor(
    private val local: RestaurantDatasource.Local,
    private val remote: RestaurantDatasource.Remote
) : RestaurantRepository {
    override fun searchRestaurantsByProperty(
        latitude: String,
        longtitude: String,
        mealType: String?,
        restaurantType: String?,
        callback: OnDataCallback<List<Restaurant>>
    ) {
        val parameters = getDefaultParams()
        parameters.apply {
            put(ApiEndpoint.LATITUDE, latitude)
            put(ApiEndpoint.LONGTITUDE, longtitude)
            mealType?.let {
                put(MEAL_TYPE, it)
            }
            restaurantType?.let {
                put(RESTAURANT_TYPE, it.toString())
            }
        }
        remote.searchRestaurantsByProperty(parameters, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                try {
                    val jsonObject = JSONObject(data)
                    val jsonArray = jsonObject.getJSONArray(DATA)
                    val listRestaurant = Restaurant.jsonToArray(jsonArray)
                    callback.onSuccess(listRestaurant)
                } catch (exception: JSONException) {
                    callback.onError(exception)
                }
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }

    override fun getDetailRestaurant(
        locationId: String,
        callback: OnDataCallback<Restaurant>
    ) {
        val parameters = getDefaultParams()
        parameters.put(LOCATION_ID, locationId)
        remote.getDetailRestaurant(parameters, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                try {
                    val jsonObject = JSONObject(data)
                    val jsonArray = jsonObject.getJSONArray(DATA)
                    val restaurant = Restaurant(jsonArray.getJSONObject(0))
                    callback.onSuccess(restaurant)
                } catch (exception: JSONException) {
                    callback.onError(exception)
                }
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }

    override fun getDefaultParams(): MutableMap<String, String> = local.getDefaultParams()

    override fun insertLocation(restaurant: Restaurant, callback: OnDataCallback<Boolean>) {
        local.insertLocation(restaurant, callback)
    }

    companion object {
        @Volatile
        private var INSTANCE: RestaurantRepository? = null

        fun getInstance(
            local: RestaurantDatasource.Local,
            remote: RestaurantDatasource.Remote
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RestaurantRepositoryImpl(local, remote).also { INSTANCE = it }
        }
    }
}
