package com.sunasterisk.travelapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class Location(
    val id: String,
    val type: String,
    val name: String,
    val location: String,
    var thumb: String,
    var large: String,
    var descriptionLocation: String
) : Parcelable {
    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject, type: String, description: String) : this(
        jsonObject.getString(LOCATION_ID),
        type,
        jsonObject.getString(NAME),
        jsonObject.getString(LOCATION_STRING),
        jsonObject
            .getJSONObject(PHOTO)
            .getJSONObject(IMAGES)
            .getJSONObject(THUMB_IMAGE)
            .getString(URL),
        jsonObject
            .getJSONObject(PHOTO)
            .getJSONObject(IMAGES)
            .getJSONObject(LARGE_IMAGE)
            .getString(URL),
        description
    )

    companion object {
        private const val LOCATION_ID = "location_id"
        private const val NAME = "name"
        private const val LOCATION_STRING = "location_string"
        private const val PHOTO = "photo"
        private const val IMAGES = "images"
        private const val LARGE_IMAGE = "large"
        private const val THUMB_IMAGE = "medium"
        private const val URL = "url"
        private const val DESCRIPTION = "description"
        private const val LOCATION_TYPE = "result_type"
        private const val LOCATION_OBJECT = "result_object"
        private const val TYPE_GEO = "geos"
        private const val GEO_DESCRIPTION = "geo_description"

        fun jsonToArray(jsonArray: JSONArray): MutableList<Location> {
            val mutableList = mutableListOf<Location>()
            for (index in 0 until jsonArray.length()) {
                try {
                    val jsonObject = jsonArray
                        .getJSONObject(index)
                    val type = jsonObject.getString(LOCATION_TYPE)
                    val locationJSONObject = jsonObject.getJSONObject(LOCATION_OBJECT)
                    val description = when (type) {
                        TYPE_GEO -> locationJSONObject.getString(GEO_DESCRIPTION)
                        else -> locationJSONObject.getString(DESCRIPTION)
                    }
                    mutableList.add(Location(locationJSONObject, type, description))
                } catch (jsonException: JSONException) {
                    jsonException.printStackTrace()
                }
            }
            return mutableList
        }
    }
}
