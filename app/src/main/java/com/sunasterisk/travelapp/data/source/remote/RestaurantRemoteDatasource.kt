package com.sunasterisk.travelapp.data.source.remote

import android.net.Uri
import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.source.RestaurantDatasource
import com.sunasterisk.travelapp.data.source.remote.utils.HttpUtils
import com.sunasterisk.travelapp.utils.ApiEndpoint.BASE_URL
import com.sunasterisk.travelapp.utils.ApiEndpoint.GET_DETAILS
import com.sunasterisk.travelapp.utils.ApiEndpoint.LIST
import com.sunasterisk.travelapp.utils.ApiEndpoint.SCHEMA

class RestaurantRemoteDatasource private constructor() : RestaurantDatasource.Remote {
    override fun searchRestaurantsByProperty(
        prameters: Map<String, String>,
        callback: OnDataCallback<String>
    ) {
        val uri = Uri.Builder()
            .scheme(SCHEMA)
            .authority(BASE_URL)
            .appendPath(RESTAURANTS)
            .appendPath(LIST)
        prameters.forEach {
            uri.appendQueryParameter(it.key, it.value)
        }
        val url = uri.build().toString()
        LoadDataAsync<String, String>(callback){
            HttpUtils.getApi(it)
        }.execute(url)
    }

    override fun getDetailRestaurant(
        prameters: Map<String, String>,
        callback: OnDataCallback<String>
    ) {
        val uri = Uri.Builder()
            .scheme(SCHEMA)
            .authority(BASE_URL)
            .appendPath(RESTAURANTS)
            .appendPath(GET_DETAILS)
        prameters.forEach {
            uri.appendQueryParameter(it.key, it.value)
        }
        val url = uri.build().toString()
        LoadDataAsync<String, String>(callback){
            HttpUtils.getApi(it)
        }.execute(url)
    }

    companion object {
        private const val RESTAURANTS = "restaurants"

        @Volatile
        private var INSTANCE: RestaurantRemoteDatasource? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RestaurantRemoteDatasource().also { INSTANCE = it }
            }
    }
}
