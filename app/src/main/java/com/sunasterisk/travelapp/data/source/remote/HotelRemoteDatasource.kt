package com.sunasterisk.travelapp.data.source.remote

import android.net.Uri
import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.source.HotelDatasource
import com.sunasterisk.travelapp.data.source.remote.utils.HttpUtils
import com.sunasterisk.travelapp.utils.ApiEndpoint.BASE_URL
import com.sunasterisk.travelapp.utils.ApiEndpoint.GET_DETAILS
import com.sunasterisk.travelapp.utils.ApiEndpoint.LIST
import com.sunasterisk.travelapp.utils.ApiEndpoint.SCHEMA

class HotelRemoteDatasource private constructor() : HotelDatasource.Remote {
    override fun searchHotelsByProperty(
        prameters: Map<String, String>,
        callback: OnDataCallback<String>
    ) {
        val uri = Uri.Builder()
            .scheme(SCHEMA)
            .authority(BASE_URL)
            .appendPath(HOTELS)
            .appendPath(LIST)
        prameters.forEach {
            uri.appendQueryParameter(it.key, it.value)
        }
        val url = uri.build().toString()
        LoadDataAsync<String, String>(callback) {
            HttpUtils.getApi(it)
        }.execute(url)
    }

    override fun getDetailHotel(
        prameters: Map<String, String>,
        callback: OnDataCallback<String>
    ) {
        val uri = Uri.Builder()
            .scheme(SCHEMA)
            .authority(BASE_URL)
            .appendPath(HOTELS)
            .appendPath(GET_DETAILS)
        prameters.forEach {
            uri.appendQueryParameter(it.key, it.value)
        }
        val url = uri.build().toString()
        LoadDataAsync<String, String>(callback) {
            HttpUtils.getApi(it)
        }.execute(url)
    }

    companion object {
        const val HOTELS = "hotels"

        @Volatile
        private var INSTANCE: HotelRemoteDatasource? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HotelRemoteDatasource().also { INSTANCE = it }
            }
    }
}
