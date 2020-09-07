package com.sunasterisk.travelapp.data.models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

data class Review(
    val author: String,
    var title: String,
    var rating: Double,
    var publishDate: String,
    var summary: String
) {
    @Throws(JSONException::class)
    constructor(jsonObject: JSONObject) : this(
        jsonObject.getString(AUTHOR),
        jsonObject.getString(TITLE),
        jsonObject.getString(RATING_COUNT).toDouble(),
        jsonObject.getString(PUBLISH_DATE),
        jsonObject.getString(SUMMARY)
    )

    companion object {
        const val AUTHOR = "author"
        const val TITLE = "title"
        const val SUMMARY = "summary"
        const val PUBLISH_DATE = "published_date"
        const val RATING_COUNT = "rating"

        @Throws(JSONException::class)
        fun jsonToArray(jsonArray: JSONArray): MutableList<Review> {
            val mutableList = mutableListOf<Review>()
            for (index in 0 until jsonArray.length()) {
                mutableList.add(Review(jsonArray.getJSONObject(index)))
            }
            return mutableList
        }
    }
}
