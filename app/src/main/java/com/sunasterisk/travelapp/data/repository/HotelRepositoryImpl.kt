package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Hotel
import com.sunasterisk.travelapp.data.source.HotelDatasource
import com.sunasterisk.travelapp.utils.ApiEndpoint.ADULTS
import com.sunasterisk.travelapp.utils.ApiEndpoint.CHECK_IN_DATE
import com.sunasterisk.travelapp.utils.ApiEndpoint.CHILDRENS
import com.sunasterisk.travelapp.utils.ApiEndpoint.LATITUDE
import com.sunasterisk.travelapp.utils.ApiEndpoint.LOCATION_ID
import com.sunasterisk.travelapp.utils.ApiEndpoint.LONGTITUDE
import com.sunasterisk.travelapp.utils.KeyResponse.DATA
import org.json.JSONException
import org.json.JSONObject

class HotelRepositoryImpl private constructor(
    private val local: HotelDatasource.Local,
    private val remote: HotelDatasource.Remote
) : HotelRepository {

    override fun searchHotelsByProperty(
        latitude: String,
        longtitude: String,
        checkInDate: String?,
        adults: Int?,
        childrens: Int?,
        callback: OnDataCallback<List<Hotel>>
    ) {
        val parameters = getDefaultParams()
        parameters.apply {
            put(LATITUDE, latitude)
            put(LONGTITUDE, longtitude)
            checkInDate?.let {
                put(CHECK_IN_DATE, it)
            }
            adults?.let {
                put(ADULTS, it.toString())
            }
            childrens?.let {
                put(CHILDRENS, it.toString())
            }
        }
        remote.searchHotelsByProperty(parameters, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                try {
                    val jsonObject = JSONObject(data)
                    val jsonArray = jsonObject.getJSONArray(DATA)
                    val listHotel = Hotel.jsonToArray(jsonArray)
                    callback.onSuccess(listHotel)
                } catch (exception: JSONException) {
                    callback.onError(exception)
                }
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }

    override fun getDetailHotel(locationId: String, callback: OnDataCallback<Hotel>) {
        val parameters = getDefaultParams()
        parameters[LOCATION_ID] = locationId
        remote.getDetailHotel(parameters, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                try {
                    val jsonObject = JSONObject(data)
                    val jsonArray = jsonObject.getJSONArray(DATA)
                    val hotel = Hotel(jsonArray.getJSONObject(0))
                    callback.onSuccess(hotel)
                } catch (exception: JSONException) {
                    callback.onError(exception)
                }
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }

    override fun insertLocation(hotel: Hotel, callback: OnDataCallback<Boolean>) {
        local.insertLocation(hotel, callback)
    }

    override fun getDefaultParams(): MutableMap<String, String> = local.getDefaultParams()

    companion object {
        @Volatile
        private var INSTANCE: HotelRepository? = null

        fun getInstance(
            local: HotelDatasource.Local,
            remote: HotelDatasource.Remote
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: HotelRepositoryImpl(local, remote).also { INSTANCE = it }
        }
    }
}
