package com.sunasterisk.travelapp.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import kotlin.jvm.Throws

@Parcelize
data class Hotel(
    val id: String,
    val name: String = "",
    val location: String = "",
    val address: String = "",
    var imageThumbHotel: String = "",
    var imageLargeHotel: String = "",
    var price: String = "",
    var descriptionHotel: String = "",
    var rating_count: Double = 0.0,
    var type: String = ""
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
        jsonObject.getString(DESCRIPTION),
        jsonObject.getDouble(RATING_COUNT),
        jsonObject.getString(TYPE)
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
        private const val RATING_COUNT = "rating"
        private const val TYPE = "subcategory_type_label"

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
                            price = hotelJSONObject.getString(PRICE),
                            rating_count = hotelJSONObject.getDouble(RATING_COUNT),
                            type = hotelJSONObject.getString(TYPE)
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
