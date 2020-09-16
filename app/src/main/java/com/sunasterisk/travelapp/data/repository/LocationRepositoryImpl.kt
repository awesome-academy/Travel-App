package com.sunasterisk.travelapp.data.repository

import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.models.Location
import com.sunasterisk.travelapp.data.source.LocationDatasource
import com.sunasterisk.travelapp.utils.ApiEndpoint.LOCATION_ID
import com.sunasterisk.travelapp.utils.ApiEndpoint.QUERY
import com.sunasterisk.travelapp.utils.KeyResponse.DATA
import org.json.JSONException
import org.json.JSONObject

class LocationRepositoryImpl private constructor(
    private val local: LocationDatasource.Local,
    private val remote: LocationDatasource.Remote
) : LocationRepository {

    override fun searchLocationsByProperty(
        query: String,
        callback: OnDataCallback<List<Location>>
    ) {
        val parameters = getDefaultParams()
        parameters[QUERY] = query
        remote.searchLocationsByProperty(parameters, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                try {
                    val jsonObject = JSONObject(data)
                    val jsonArray = jsonObject.getJSONArray(DATA)
                    val listLocation = Location.jsonToArray(jsonArray)
                    callback.onSuccess(listLocation)
                } catch (exception: JSONException) {
                    callback.onError(exception)
                }
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }

    override fun getPhotos(locationId: String, callback: OnDataCallback<List<String>>) {
        val params = getDefaultParams()
        params[LOCATION_ID] = locationId
        remote.getPhotos(params, object : OnDataCallback<String> {
            override fun onSuccess(data: String) {
                try {
                    val jsonObject = JSONObject(data)
                    val jsonArray = jsonObject.getJSONArray(DATA)
                    val listPhotos = mutableListOf<String>()
                    for (index in 0 until jsonArray.length()) {
                        listPhotos.add(jsonArray.getString(index))
                    }
                    callback.onSuccess(listPhotos)
                } catch (exception: JSONException) {
                    callback.onError(exception)
                }
            }

            override fun onError(throwable: Throwable) {
                callback.onError(throwable)
            }
        })
    }

    override fun insertLocation(location: Location, callback: OnDataCallback<Boolean>) {
        local.insertLocation(location, callback)
    }

    override fun getAllLocations(callback: OnDataCallback<List<Location>>) {
        local.getAllLocations(callback)
    }

    override fun deleteLocation(location: Location, callback: OnDataCallback<Boolean>) {
        local.deleteLocation(location, callback)
    }

    override fun getDefaultParams(): MutableMap<String, String> = local.getDefaultParams()

    companion object {
        @Volatile
        private var INSTANCE: LocationRepository? = null

        fun getInstance(
            local: LocationDatasource.Local,
            remote: LocationDatasource.Remote
        ) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: LocationRepositoryImpl(local, remote).also { INSTANCE = it }
        }
    }
}
