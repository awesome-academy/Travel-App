package com.sunasterisk.travelapp.data.source.remote

import android.net.Uri
import com.sunasterisk.travelapp.data.LoadDataAsync
import com.sunasterisk.travelapp.data.OnDataCallback
import com.sunasterisk.travelapp.data.source.LocationDatasource
import com.sunasterisk.travelapp.data.source.remote.utils.HttpUtils
import com.sunasterisk.travelapp.utils.ApiEndpoint.BASE_URL
import com.sunasterisk.travelapp.utils.ApiEndpoint.LIST
import com.sunasterisk.travelapp.utils.ApiEndpoint.SCHEMA

class LocationRemoteDatasource private constructor() : LocationDatasource.Remote {
    override fun searchLocationsByProperty(
        prameters: Map<String, String>,
        callback: OnDataCallback<String>
    ) {
        val uri = Uri.Builder()
            .scheme(SCHEMA)
            .authority(BASE_URL)
            .appendPath(LOCATIONS)
            .appendPath(SEARCH)
        prameters.forEach {
            uri.appendQueryParameter(it.key, it.value)
        }
        val url = uri.build().toString()
        LoadDataAsync<String, String>(callback){
            HttpUtils.getApi(it)
        }.execute(url)
    }

    override fun getPhotos(prameters: Map<String, String>, callback: OnDataCallback<String>) {
        val uri = Uri.Builder()
            .scheme(SCHEMA)
            .authority(BASE_URL)
            .appendPath(PHOTOS)
            .appendPath(LIST)
        prameters.forEach {
            uri.appendQueryParameter(it.key, it.value)
        }
        val url = uri.build().toString()
        LoadDataAsync<String, String>(callback){
            HttpUtils.getApi(it)
        }.execute(url)
    }

    companion object {
        private const val LOCATIONS = "locations"
        private const val SEARCH = "search"
        private const val PHOTOS = "photos"

        @Volatile
        private var INSTANCE: LocationRemoteDatasource? = null

        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationRemoteDatasource().also { INSTANCE = it }
            }
    }
}
