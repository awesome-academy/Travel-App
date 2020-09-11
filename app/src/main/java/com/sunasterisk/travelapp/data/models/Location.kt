package com.sunasterisk.travelapp.data.models

import android.content.ContentValues
import android.database.Cursor
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
    var descriptionLocation: String,
    var propertiesNumber: Int = 0
) : Parcelable {
    @Throws(JSONException::class)
    constructor(
        jsonObject: JSONObject,
        type: String,
        description: String,
        propertiesNumber: Int
    ) : this(
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
        description,
        propertiesNumber
    )

    constructor(cursor: Cursor) : this(
        cursor.getString(cursor.getColumnIndex(ID)),
        cursor.getString(cursor.getColumnIndex(LOCATION_NAME)),
        cursor.getString(cursor.getColumnIndex(TYPE)),
        cursor.getString(cursor.getColumnIndex(LOCATION)),
        cursor.getString(cursor.getColumnIndex(THUMB)),
        cursor.getString(cursor.getColumnIndex(LARGE)),
        cursor.getString(cursor.getColumnIndex(DES)),
        cursor.getInt(cursor.getColumnIndex(PROPERTIES_NUMBER))
    )

    fun getValue(user: User): ContentValues =
        ContentValues().apply {
            put(ID, id)
            put(User.ID, user.id)
            put(TYPE, type)
            put(LOCATION_NAME, name)
            put(LOCATION, location)
            put(THUMB, thumb)
            put(LARGE, large)
            put(DES, descriptionLocation)
            put(PROPERTIES_NUMBER, propertiesNumber)
        }

    companion object {
        private const val LOCATION_ID = "location_id"
        private const val NAME = "name"
        private const val LOCATION_STRING = "location_string"
        private const val RESTAURANTS = "restaurants"
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
        private const val CATEGORY_COUNT = "category_counts"
        private const val TOTAL = "total"

        const val TABLE_NAME = "location"
        const val ID = "location_Id"
        const val TYPE = "type"
        const val LOCATION_NAME = "name"
        const val LOCATION = "location"
        const val THUMB = "thumb"
        const val LARGE = "large"
        const val DES = "description"
        const val PROPERTIES_NUMBER = "properties_number"

        fun jsonToArray(jsonArray: JSONArray): MutableList<Location> {
            val mutableList = mutableListOf<Location>()
            for (index in 0 until jsonArray.length()) {
                try {
                    val jsonObject = jsonArray
                        .getJSONObject(index)
                    val type = jsonObject.getString(LOCATION_TYPE)
                    val locationJSONObject = jsonObject.getJSONObject(LOCATION_OBJECT)
                    var description: String
                    var propertiesNumber = 0
                    if (TYPE_GEO.equals(type)) {
                        description = locationJSONObject.getString(GEO_DESCRIPTION)
                        propertiesNumber =
                            locationJSONObject
                                .getJSONObject(CATEGORY_COUNT)
                                .getJSONObject(RESTAURANTS)
                                .getString(TOTAL).toInt()
                    } else {
                        description = locationJSONObject.getString(DESCRIPTION)
                    }
                    mutableList.add(
                        Location(
                            locationJSONObject,
                            type,
                            description,
                            propertiesNumber
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
