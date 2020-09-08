package com.sunasterisk.travelapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

@Parcelize
data class Hotel(
    val id: String,
    val name: String = "",
    val location: String = "",
    val address: String = "",
    var imageThumbHotel: String = "",
    var imageLargeHotel: String = "",
    var price: String = "",
    var descriptionHotel: String = ""
) : Parcelable {

    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(LOCATION_ID),
        jsonObject.getString(NAME),
        jsonObject.getString(LOCATION_STRING),
        jsonObject.getString(ADDRESS),
        jsonObject.getJSONObject(PHOTO)
            .getJSONObject(IMAGES)
            .getJSONObject(THUMB_IMAGE)
            .getString(URL),
        jsonObject.getJSONObject(PHOTO)
            .getJSONObject(IMAGES)
            .getJSONObject(LARGE_IMAGE)
            .getString(URL),
        jsonObject.getString(PRICE),
        jsonObject.getString(DESCRIPTION)
    )

    companion object {
        private const val LOCATION_ID = "location_id"
        private const val NAME = "name"
        private const val LOCATION_STRING = "location_string"
        private const val ADDRESS = "address"
        private const val PHOTO = "photo"
        private const val IMAGES = "images"
        private const val LARGE_IMAGE = "large"
        private const val THUMB_IMAGE = "medium"
        private const val URL = "url"
        private const val PRICE = "price"
        private const val DESCRIPTION = "description"

        fun jsonToArray(jsonArray: JSONArray): MutableList<Hotel> {
            val mutableList = mutableListOf<Hotel>()
            for (index in 0 until jsonArray.length()) {
                try {
                    val hotelJSONObject = jsonArray.getJSONObject(index)
                    val thumbJSONObject = hotelJSONObject
                        .getJSONObject(PHOTO)
                        .getJSONObject(IMAGES)
                        .getJSONObject(THUMB_IMAGE)
                    mutableList.add(
                        Hotel(
                            id = hotelJSONObject.getString(LOCATION_ID),
                            name = hotelJSONObject.getString(NAME),
                            imageThumbHotel = thumbJSONObject.getString(URL),
                            location = hotelJSONObject.getString(LOCATION_STRING),
                            price = hotelJSONObject.getString(PRICE)
                        )
                    )
                } catch (jsonException: JSONException) {
                    jsonException.printStackTrace()
                }
            }
            return mutableList
        }
    }
}
